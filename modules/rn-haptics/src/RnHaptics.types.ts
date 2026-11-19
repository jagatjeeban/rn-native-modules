// Define your exported module types here.
export type RnHapticsModuleEvents = {}

export type HapticStyle = "light" | "medium" | "heavy"

export interface RnHapticsModule {
  triggerHaptic(style: HapticStyle): Promise<void>
}
