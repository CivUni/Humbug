package com.untamedears.humbug;


import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.ItemEnderPearl;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.SoundCategory;
import net.minecraft.server.v1_11_R1.SoundEffects;
import net.minecraft.server.v1_11_R1.StatisticList;
import net.minecraft.server.v1_11_R1.World;

@Deprecated
public class CustomNMSItemEnderPearl extends ItemEnderPearl {
  private Config cfg_;

  public CustomNMSItemEnderPearl(Config cfg) {
    super();
    cfg_ = cfg;
  }

  public ItemStack a(
      ItemStack itemstack,
      World world,
      EntityHuman entityhuman) {
    if (!entityhuman.abilities.canInstantlyBuild) {
      itemstack.subtract(1);
    }

    world.a((EntityHuman) null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.bg, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemEnderPearl.j.nextFloat() * 0.4F + 0.8F));
	entityhuman.di().a(this, 20);
    if (!world.isClientSide) {
      double gravity = cfg_.get("ender_pearl_gravity").getDouble();
      CustomNMSEntityEnderPearl prl = new CustomNMSEntityEnderPearl(world, entityhuman, gravity);
	  prl.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 1.5F, 1.0F);

	  world.addEntity(prl);
    }
    entityhuman.b(StatisticList.b((Item) this));
    return itemstack;
  }
}
