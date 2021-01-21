package com.iridium.iridiumskyblock.database.orm;

import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * ORM class representing a user in the database
 *
 * @author BomBardyGamer
 * @since 3.0
 */
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@DatabaseTable(tableName = "users")
public final class User {

    @DatabaseField(columnName = "id", generatedId = true)
    @Nullable Integer id;

    @DatabaseField(columnName = "uuid", canBeNull = false)
    @NotNull UUID uuid;

    @DatabaseField(columnName = "name", canBeNull = false)
    @NotNull String name;

    @DatabaseField(columnName = "island_id", foreign = true)
    @NotNull Island island;

    @DatabaseField(columnName = "role")
    @Nullable Role role;

    @DatabaseField(columnName = "last_creation_time", canBeNull = false)
    @Nullable LocalDateTime lastCreationTime; // May not fully work yet, still waiting on java.time support for ormlite

    public User(final @NotNull UUID uuid, final @NotNull String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
