package me.babbaj.asmlibforge.asm.transformers;

import net.futureclient.asm.transformer.AsmMethod;
import net.futureclient.asm.transformer.annotation.Inject;
import net.futureclient.asm.transformer.annotation.Transformer;
import net.minecraft.client.Minecraft;

@Transformer(Minecraft.class)
public class TestTransformer {

    @Inject(name = "runTick")
    public void inject(AsmMethod method) {
        method.run(() -> System.out.println("tick event"));
    }
}
