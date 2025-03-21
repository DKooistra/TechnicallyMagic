package net.technicallyMagic.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.technicallyMagic.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class checkValidBlockItem extends Item {
    public checkValidBlockItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            BlockState state = pContext.getLevel().getBlockState(positionClicked);

            if (isValidBlock(state)) {
                sendConfirmMessage(positionClicked, player, state.getBlock());
            } else {
                player.sendSystemMessage(Component.literal("Not valid block"));
            }

        }


        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Check logic for valid blocks"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void sendConfirmMessage(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Is valid block: " + I18n.get(block.getDescriptionId())));
    }

    private boolean isValidBlock(BlockState state) {
        return state.is(ModTags.Blocks.TRANSMUTABLE_BLOCKS);
    }
}
