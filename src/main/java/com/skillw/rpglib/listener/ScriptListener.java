package com.skillw.rpglib.listener;

import com.skillw.rpglib.RPGLib;
import io.izzel.taboolib.common.event.PlayerAttackEvent;
import io.izzel.taboolib.common.event.PlayerJumpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.*;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class ScriptListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockCook(BlockCookEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockExp(BlockExpEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockFertilize(BlockFertilizeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onBlockShearEntity(BlockShearEntityEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onCreeperPower(CreeperPowerEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEnderDragonChangePhase(EnderDragonChangePhaseEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityMount(EntityMountEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityPortalEnter(EntityPortalEnterEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityPoseChange(EntityPoseChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityPotionEffect(EntityPotionEffectEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityResurrect(EntityResurrectEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityTeleport(EntityTeleportEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityToggleGlide(EntityToggleGlideEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityToggleSwim(EntityToggleSwimEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityTransform(EntityTransformEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onEntityUnleash(EntityUnleashEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onFireworkExplode(FireworkExplodeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onFluidLevelChange(FluidLevelChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onHangingBreak(HangingBreakEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onHangingPlace(HangingPlaceEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onHorseJump(HorseJumpEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onTradeSelect(TradeSelectEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onInventoryPickupItem(InventoryPickupItemEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onItemMerge(ItemMergeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onLightningStrike(LightningStrikeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onLootGenerate(LootGenerateEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onMapInitialize(MapInitializeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onMoistureChange(MoistureChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onNotePlay(NotePlayEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPigZombieAnger(PigZombieAngerEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerAnimation(PlayerAnimationEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerChangedMainHand(PlayerChangedMainHandEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerAttack(PlayerAttackEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerChannel(PlayerChannelEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerCommandSend(PlayerCommandSendEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerEggThrow(PlayerEggThrowEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerItemBreak(PlayerItemBreakEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerItemMend(PlayerItemMendEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerLeashEntity(PlayerLeashEntityEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerResourcePackStatus(PlayerResourcePackStatusEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerRiptide(PlayerRiptideEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerShearEntity(PlayerShearEntityEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerSpawnLocation(PlayerSpawnLocationEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerTakeLecternBook(PlayerTakeLecternBookEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPortalCreate(PortalCreateEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onPrepareItemEnchant(PrepareItemEnchantEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onSheepDyeWool(SheepDyeWoolEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onSheepRegrowWool(SheepRegrowWoolEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onSpawnChange(SpawnChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onStructureGrow(StructureGrowEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onThunderChange(ThunderChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVehicleCreate(VehicleCreateEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVillagerAcquireTrade(VillagerAcquireTradeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onVillagerReplenishTrade(VillagerReplenishTradeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onWorldSave(WorldSaveEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent event) {
        RPGLib.getScriptEventManager().handleEvent(event);
    }
}
