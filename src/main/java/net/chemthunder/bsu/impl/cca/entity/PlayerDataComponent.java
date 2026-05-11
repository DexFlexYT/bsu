package net.chemthunder.bsu.impl.cca.entity;

import net.chemthunder.bsu.impl.BasicallyServerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class PlayerDataComponent implements AutoSyncedComponent {
    public static final ComponentKey<PlayerDataComponent> KEY = ComponentRegistry.getOrCreate(BasicallyServerUtils.id("data"), PlayerDataComponent.class);
    private final PlayerEntity player;

    private String name = "";
    private String pronoun = "";

    private int r = 0;
    private int g = 0;
    private int b = 0;

    public PlayerDataComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.name = nbt.getString("Name");
        this.pronoun = nbt.getString("Pronoun");

        this.r = nbt.getInt("R");
        this.g = nbt.getInt("G");
        this.b = nbt.getInt("B");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putString("Name", name);
        nbt.putString("Pronoun", pronoun);

        nbt.putInt("R", r);
        nbt.putInt("G", g);
        nbt.putInt("B", b);
    }

    public String getName() {
        return this.name;
    }

    public String getPronoun() {
        return this.pronoun;
    }

    public void setName(String name) {
        this.name = name;
        this.sync();
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
        this.sync();
    }

    public void set(String name, String pronoun) {
        this.name = name;
        this.pronoun = pronoun;
        this.sync();
    }

    public int getR() {
        return this.r;
    }

    public int getG() {
        return this.g;
    }

    public int getB() {
        return this.b;
    }

    public void setR(int r) {
        this.r = r;
        this.sync();
    }

    public void setG(int g) {
        this.g = g;
        this.sync();
    }

    public void setB(int b) {
        this.b = b;
        this.sync();
    }

    public void setRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;

        this.sync();
    }
}
