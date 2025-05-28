var desktop = automation.GetDesktop();

// Step 1: Get visible lists
var allLists = desktop.FindAllDescendants(cf => cf.ByControlType(ControlType.List)
                                                  .And(cf.ByFrameworkId("WinForm")));

AutomationElement correctList = null;

foreach (var list in allLists)
{
    if (!list.IsOffscreen && list.FindAllChildren().Length > 0)
    {
        correctList = list;
        break;
    }
}

if (correctList != null)
{
    // Step 2: Drill into nested lists to find list items
    var listItems = correctList.FindAllDescendants(cf => cf.ByControlType(ControlType.ListItem));

    foreach (var item in listItems)
    {
        Console.WriteLine("Found item: " + item.Name);
    }

    // Step 3: Select item by text
    var targetItem = listItems.FirstOrDefault(i => i.Name == "India");
    if (targetItem != null)
    {
        try
        {
            targetItem.Click();
            Console.WriteLine("Clicked 'India'");
        }
        catch
        {
            Console.WriteLine("Click failed, using fallback...");
            if (targetItem.Patterns.Invoke.IsSupported)
                targetItem.Patterns.Invoke.Pattern.Invoke();
            else
            {
                targetItem.Focus();
                FlaUI.Core.Input.Keyboard.Press(FlaUI.Core.WindowsAPI.VirtualKeyShort.ENTER);
            }
        }
    }
    else
    {
        Console.WriteLine("'India' not found in list items.");
    }
}
else
{
    Console.WriteLine("No visible List found.");
}
