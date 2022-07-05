package tensor.mixin.feature;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tensor.option.TensorOptions;

@Mixin(ClientPlayerEntity.class)
public class ChatEmoji_ClientPlayerEntity
{
    @ModifyVariable
    (
        method = "sendChatMessage(Ljava/lang/String;Lnet/minecraft/text/Text;)V",
        at = @At("HEAD"),
        ordinal = 0
    )
    public String changeEmoji(String message)
    {
        if(TensorOptions.waveChatEmote)
            message = message.replaceAll("o/", "( ﾟ◡ﾟ)/");
        if(TensorOptions.shrugChatEmote)
            message = message.replaceAll(":shrug:", "¯\\\\_(ツ)_/¯");
        if(TensorOptions.tableflipChatEmote)
            message = message.replaceAll(":tableflip:", "(╯°□°)╯︵ ┻━┻");
        if(TensorOptions.tablebackChatEmote)
            message = message.replaceAll(":tableback:", "┬─┬ ノ( ゜-゜ノ)");
        if(TensorOptions.gimmeChatEmote)
            message = message.replaceAll(":gimme:", "༼つ ◕_◕ ༽つ");
        if(TensorOptions.wizardChatEmote)
            message = message.replaceAll(":wizard:", "('-')⊃━☆ﾟ.*･｡ﾟ");
        if(TensorOptions.skullChatEmote)
            message = message.replaceAll(":skull:", "☠");
        if(TensorOptions.susChatEmote)
            message = message.replaceAll(":sus:", "ඞ");
        if(TensorOptions.heartChatEmote)
            message = message.replaceAll(":heart:", "❤");
        if(TensorOptions.starChatEmote)
            message = message.replaceAll(":star:", "✮");
        if(TensorOptions.arrowChatEmote)
            message = message.replaceAll(":arrow:", "➜");
        if(TensorOptions.javaChatEmote)
            message = message.replaceAll(":java:", "☕");
        if(TensorOptions.confusedChatEmote)
            message = message.replaceAll(":confused:", "¯\\(°_o)/¯");
        if(TensorOptions.unamusedChatEmote)
            message = message.replaceAll(":unamused:", "(ㆆ_ㆆ)");
        if(TensorOptions.prayChatEmote)
            message = message.replaceAll(":pray:", "(╯˘ -˘ )╯");
        if(TensorOptions.kirbyChatEmote)
            message = message.replaceAll(":kirby:", "(っ^‿^)っ");
        return message;
    }
}
