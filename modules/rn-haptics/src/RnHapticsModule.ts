import { NativeModule, requireNativeModule } from 'expo';
import { HapticStyle, RnHapticsModuleEvents } from './RnHaptics.types';

declare class RnHapticsModule extends NativeModule<RnHapticsModuleEvents> {
  triggerHaptic(style: HapticStyle): Promise<void>;
}

export default requireNativeModule<RnHapticsModule>('RnHaptics');
