package com.example.pokedex.service

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class PokemonMigrations {
    companion object{
        val migration_1_2 = object : Migration(1,2){

            // alteração do que deve ser NOT NULL para NULL
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE 'Pokemon_Novo' "+
                            "('id' TEXT PRIMARY KEY NOT NULL, "+
                            "'nome' TEXT NOT NULL, "+
                            "'types' TEXT NOT NULL, "+
                            "'abilities' TEXT, "+
                            "'hp' TEXT NOT NULL )"
                )

                database.execSQL(
                    "INSERT INTO Pokemon_Novo (" +
                            "id, nome, types, abilities, hp)" +
                            "SELECT id, nome, types, abilities, hp FROM PokemonItem"
                )
                database.execSQL("DROP TABLE PokemonItem")
                database.execSQL("ALTER TABLE Pokemon_Novo RENAME TO PokemonItem")
            }


        }

    }
}


