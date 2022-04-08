package hr.algebra.covidapp

import android.content.*
import android.database.Cursor
import android.net.Uri
import hr.algebra.covidapp.dao.CountriesSqlHelper
import hr.algebra.covidapp.dao.CovidRepository

import hr.algebra.covidapp.factory.getCountriesRepository
import hr.algebra.covidapp.model.Item
import java.lang.IllegalArgumentException

private const val AUTHORITY = "hr.algebra.covid.api.provider"
private const val PATH = "total"
val COVID_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20
private const val TOTAL = "TOTAL"

private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, ITEMS)
    addURI(AUTHORITY, "$PATH/#", ITEM_ID)
    this
}
private const val CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH
private const val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH

class CovidProvider : ContentProvider() {
    private lateinit var repository: CountriesSqlHelper

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)){
            ITEMS -> return  repository.delete(TOTAL,selection,selectionArgs)
            ITEM_ID -> {
                val id= uri.lastPathSegment
                if (id!=null){
                    return repository.delete(TOTAL,"${Item::_id.name} =?", arrayOf(id))
                }

            }
        }
        throw  IllegalArgumentException("Wrong  URI")
    }

    override fun getType(uri: Uri): String? {
        when(URI_MATCHER.match(uri)){
            ITEMS -> return CONTENT_DIR_TYPE
            ITEM_ID -> return  CONTENT_ITEM_TYPE
        }
        throw  IllegalArgumentException("Wrong URI")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id=repository.insert(TOTAL,values)
        return ContentUris.withAppendedId(COVID_PROVIDER_CONTENT_URI,id)
    }

    override fun onCreate(): Boolean {
        repository= getCountriesRepository(context)
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? =
        repository.query(TOTAL,projection,selection,selectionArgs,sortOrder)


    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)){
            ITEMS -> return  repository.update(TOTAL,values,selection,selectionArgs)
            ITEM_ID -> {
                val id= uri.lastPathSegment
                if (id!=null){
                    return repository.update(TOTAL,values,"${Item::_id.name} =?", arrayOf(id))
                }

            }
        }
        throw  IllegalArgumentException("Wrong  URI")
    }
}