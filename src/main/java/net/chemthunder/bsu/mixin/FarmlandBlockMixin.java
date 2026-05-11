package net.chemthunder.bsu.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @WrapMethod(method = "setToDirt")
    private static void bsu$removeCropTrampling(Entity entity, BlockState state, World world, BlockPos pos, Operation<Void> original) {}
}
