package net.chemthunder.bsu.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.chemthunder.bsu.impl.cca.entity.PlayerDataComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @WrapMethod(method = "getDisplayName")
    private Text bsu$nickname(Operation<Text> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        PlayerDataComponent data = PlayerDataComponent.KEY.get(player);

        if (!data.getName().isEmpty()) {
            int color = ColorHelper.Argb.getArgb(data.getR(), data.getG(), data.getB());
            return Text.literal(data.getName()).withColor(color).append(!data.getPronoun().isEmpty() ? Text.literal(" [" + data.getPronoun() + "]") : Text.empty());
        }

        return original.call();
    }
}
