/*
 * Copyright (c) 2018-2020 C4
 *
 * This file is part of Construct's Armory, a mod made for Minecraft.
 *
 * Construct's Armory is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Construct's Armory is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Construct's Armory.  If not, see <https://www.gnu.org/licenses/>.
 */

package c4.conarm.common.armor.traits;

import c4.conarm.common.armor.utils.ArmorHelper;
import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TraitVengeful extends AbstractArmorTrait {

    private static final float MODIFIER = 0.2F;

    public TraitVengeful() {
        super("vengeful", 0xff0000);
    }

    @Override
    public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
        Entity entity = source.getTrueSource();
        if (entity instanceof EntityLivingBase && !(entity instanceof FakePlayer)) {
            if (random.nextFloat() < 0.05F) {
                Potion potion;
                switch(random.nextInt(4)) {
                    case 0:
                        potion = MobEffects.POISON;
                        break;
                    case 1:
                        potion = MobEffects.SLOWNESS;
                        break;
                    case 2:
                        potion = MobEffects.WITHER;
                        break;
                    case 3:
                        potion = MobEffects.WEAKNESS;
                        break;
                    default:
                        potion = MobEffects.POISON;
                        break;
                }
                ((EntityLivingBase) source.getTrueSource()).addPotionEffect(new PotionEffect(potion, 100, 0));
                ArmorHelper.damageArmor(armor, source, 3, player, EntityLiving.getSlotForItemStack(armor).getIndex());
            }
        }
        return super.onHurt(armor, player, source, damage, newDamage, evt);
    }
}
