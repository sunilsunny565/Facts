package com.assignment.facts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.facts.database.constants.Columns
import com.assignment.facts.database.constants.Tables
import com.assignment.facts.networkadapter.apiconstants.ApiConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Tables.TABLE_FACTS)
data class Facts(
    @PrimaryKey(autoGenerate = true)
    val id: Int
) {

    @ColumnInfo(name = Columns.ItemTitle, defaultValue = "")
    @SerializedName(ApiConstants.Title)
    @Expose
    var itemTitle: String? = ""
        get() = field ?: ""
        set(value) {
            field = value ?: ""
        }

    @ColumnInfo(name = Columns.Description, defaultValue = "")
    @SerializedName(ApiConstants.Description)
    @Expose
    var itemDescription: String? = ""
        get() = field ?: ""
        set(value) {
            field = value ?: ""
        }

    @ColumnInfo(name = Columns.ImageUrl, defaultValue = "")
    @SerializedName(ApiConstants.ImageUrl)
    @Expose
    var imageUrl: String? = ""
        get() = field ?: ""
        set(value) {
            field = value ?: ""
        }

    @ColumnInfo(name = Columns.Title, defaultValue = "")
    var mainTitle: String? = ""
        get() = field ?: ""
        set(value) {
            field = value ?: ""
        }

    var isBadImage: Boolean = false
}