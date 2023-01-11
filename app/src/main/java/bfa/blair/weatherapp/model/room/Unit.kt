package bfa.blair.weatherapp.model.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.DeleteTable.Entries
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "settings_tbl")
//data class Unit (
//    @NonNull
//    @PrimaryKey
//    @ColumnInfo(name = "unit")
//    val unit : String
//)

@Entity(tableName = "settings_tbl")
data class Unit (
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit : String)