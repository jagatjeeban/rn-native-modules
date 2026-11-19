import { View, StyleSheet, Button } from "react-native";
import RnHaptics, { HapticStyle } from "../../modules/rn-haptics";

export default function Index() {

  // Function to handle haptic feedback based on the style
  const handleHaptic = async (style: HapticStyle) => {
    try {
      await RnHaptics.triggerHaptic(style);
    } catch (error) {
      console.error("Error triggering haptic:", error);
    }
  };

  return (
    <View style={styles.container}>
      <Button
        title="Light Haptic"
        onPress={() => handleHaptic("light")}
      />
      <Button
        title="Medium Haptic"
        onPress={() => handleHaptic("medium")}
      />
      <Button
        title="Heavy Haptic"
        onPress={() => handleHaptic("heavy")}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
});
