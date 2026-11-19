import ExpoModulesCore
import UIKit

public class RnHapticsModule: Module {
  public func definition() -> ModuleDefinition {
    Name("RnHaptics")

    //Defines an async function that can be called from JS
    AsyncFunction("triggerHaptic") { (style: String) in
        DispatchQueue.main.async {
            let generator: UIImpactFeedbackGenerator
            
            switch style.lowercased(){
            case "light":
                generator = UIImpactFeedbackGenerator(style: .light)
            case "medium":
                generator = UIImpactFeedbackGenerator(style: .medium)
            case "heavy":
                generator = UIImpactFeedbackGenerator(style: .heavy)
            default:
                generator = UIImpactFeedbackGenerator(style: .light)
            }
            
            generator.prepare()
            generator.impactOccurred()
        }
    }
  }
}
