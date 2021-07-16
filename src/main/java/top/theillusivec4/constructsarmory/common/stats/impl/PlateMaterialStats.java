package top.theillusivec4.constructsarmory.common.stats.impl;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.stats.BaseMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.IToolStat;
import top.theillusivec4.constructsarmory.ConstructsArmoryMod;
import top.theillusivec4.constructsarmory.api.ArmorMaterialStatsIdentifiers;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@With
public class PlateMaterialStats extends BaseMaterialStats {

  public static final PlateMaterialStats DEFAULT = new PlateMaterialStats();

  private static final String DURABILITY_PREFIX =
      makeTooltipKey(TConstruct.getResource("durability"));
  private static final String ARMOR_PREFIX =
      makeTooltipKey(ConstructsArmoryMod.getResource("armor"));
  private static final String TOUGHNESS_PREFIX =
      makeTooltipKey(ConstructsArmoryMod.getResource("armor"));

  private static final ITextComponent DURABILITY_DESCRIPTION =
      makeTooltip(ConstructsArmoryMod.getResource("plate.durability.description"));
  private static final ITextComponent ARMOR_DESCRIPTION =
      makeTooltip(ConstructsArmoryMod.getResource("plate.armor.description"));
  private static final ITextComponent TOUGHNESS_DESCRIPTION =
      makeTooltip(ConstructsArmoryMod.getResource("plate.toughness.description"));
  private static final List<ITextComponent> DESCRIPTION =
      ImmutableList.of(DURABILITY_DESCRIPTION, ARMOR_DESCRIPTION, TOUGHNESS_DESCRIPTION);

  private float durability;
  private float armor;
  private float toughness;

  @Override
  public void encode(PacketBuffer buffer) {
    buffer.writeFloat(this.durability);
    buffer.writeFloat(this.armor);
    buffer.writeFloat(this.toughness);
  }

  @Override
  public void decode(PacketBuffer buffer) {
    this.durability = buffer.readFloat();
    this.armor = buffer.readFloat();
    this.toughness = buffer.readFloat();
  }

  @Override
  @Nonnull
  public MaterialStatsId getIdentifier() {
    return ArmorMaterialStatsIdentifiers.PLATE;
  }

  @Override
  @Nonnull
  public List<ITextComponent> getLocalizedInfo() {
    List<ITextComponent> list = new ArrayList<>();
    list.add(formatDurability(this.durability));
    list.add(formatArmor(this.armor));
    list.add(formatToughness(this.toughness));
    return list;
  }

  @Override
  @Nonnull
  public List<ITextComponent> getLocalizedDescriptions() {
    return DESCRIPTION;
  }

  /**
   * Applies formatting for durability
   */
  public static ITextComponent formatDurability(float durability) {
    return IToolStat.formatColoredMultiplier(DURABILITY_PREFIX, durability);
  }

  /**
   * Applies formatting for attack speed
   */
  public static ITextComponent formatArmor(float armor) {
    return IToolStat.formatColoredMultiplier(ARMOR_PREFIX, armor);
  }

  /**
   * Applies formatting for attack speed
   */
  public static ITextComponent formatToughness(float toughness) {
    return IToolStat.formatColoredMultiplier(TOUGHNESS_PREFIX, toughness);
  }
}