package com.natem135.hibana.mixin;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.event.CaughtFishEvent;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {

    @Shadow
    private boolean caughtFish;

    @Inject(method="onTrackedDataSet", at=@At("TAIL"))
    private void onTrackedDataSetInject(TrackedData<?> data, CallbackInfo ci) {
        if(caughtFish) {
            CaughtFishEvent event = new CaughtFishEvent();
            Hibana.getEventManager().notifyListeners(event);
        }
    }
}
