package me.babbaj.asmlibforge.asm.transformers;

import net.futureclient.asm.function.ToBooleanFunction;
import net.futureclient.asm.transformer.AsmMethod;
import net.futureclient.asm.transformer.annotation.Inject;
import net.futureclient.asm.transformer.annotation.Transformer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;


@Transformer(World.class)
public class WorldPatch {

    @Inject(name = "checkLightFor", args = {EnumSkyBlock.class, BlockPos.class}, ret = boolean.class,
            description = "Fix sky light lag"
    )
    public void checkLightFor(AsmMethod method) {
        method.visitInsn(new VarInsnNode(ALOAD, 1)); // EnumSkyBlock lightType
        method.<ToBooleanFunction<EnumSkyBlock>>invoke(lightType -> lightType == EnumSkyBlock.SKY);
        final LabelNode jump = new LabelNode();
        method.visitInsn(new JumpInsnNode(IFNE, jump));
        method.visitInsn(new InsnNode(ICONST_0));
        method.visitInsn(new InsnNode(IRETURN));
        method.visitInsn(jump);
    }
}
