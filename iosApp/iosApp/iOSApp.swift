import UIKit
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        KoinKt.initializeKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}