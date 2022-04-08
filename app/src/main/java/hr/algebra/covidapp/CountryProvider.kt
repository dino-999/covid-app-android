package hr.algebra.covidapp

import android.content.*
import android.database.Cursor
import android.net.Uri
import hr.algebra.covidapp.dao.CountriesSqlHelper
import hr.algebra.covidapp.factory.getCountriesRepository
import hr.algebra.covidapp.model.Country
import java.lang.IllegalArgumentException


private const val AUTHORITY = "hr.algebra.covidapp.api.provider"
private const val PATH = "countries"
val COUNTRIES_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20
private const val COUNTRIES = "countries"
private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, ITEMS)
    addURI(AUTHORITY, "$PATH/#", ITEM_ID)
    this
}
private const val CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH
private const val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH

class CountryProvider: ContentProvider() {

    private lateinit var repository: CountriesSqlHelper
    override fun onCreate(): Boolean {
        repository= getCountriesRepository(context)
        return true
    }

    override fun query(uri: Uri, projection: Array< String>?, selection: String?, selectionArgs: Array< String>?, sortOrder: String?): Cursor? =
            repository.query(COUNTRIES,projection,selection, selectionArgs,sortOrder)

    override fun getType(uri: Uri): String? {
        when(URI_MATCHER.match(uri)){
            ITEMS -> return CONTENT_DIR_TYPE
            ITEM_ID -> return  CONTENT_ITEM_TYPE
        }
        throw  IllegalArgumentException("Wrong URI")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id=repository.insert(COUNTRIES,values)
        return ContentUris.withAppendedId(COUNTRIES_PROVIDER_CONTENT_URI,id)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)){
            ITEMS -> return  repository.delete(COUNTRIES,selection,selectionArgs)
            ITEM_ID -> {
                val id= uri.lastPathSegment
                if (id!=null){
                    return repository.delete(COUNTRIES,"${Country::_id.name} = ?", arrayOf(id))
                }

            }
        }
        throw  IllegalArgumentException("Wrong  URI")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array< String>?): Int {
        when(URI_MATCHER.match(uri)){
            ITEMS -> return  repository.update(COUNTRIES,values,selection, selectionArgs)
            ITEM_ID -> {
                val id= uri.lastPathSegment
                if (id!=null){
                    return repository.update(COUNTRIES,values,"${Country::_id.name} = ?", arrayOf(id))
                }

            }
        }
        throw  IllegalArgumentException("Wrong  URI")
    }
}