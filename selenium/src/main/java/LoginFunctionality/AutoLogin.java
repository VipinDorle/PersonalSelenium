var desktop = automation.GetDesktop();
var allLists = desktop.FindAllDescendants(cf => cf.ByControlType(ControlType.List)
                                                  .And(cf.ByFrameworkId("WinForm")));

AutomationElement correctList = null;

foreach (var list in allLists)
{
    if (!list.IsOffscreen)
    {
        var items = list.FindAllChildren();
        if (items.Length > 0)
        {
            Console.WriteLine("Visible list found with items:");
            foreach (var item in items)
            {
                Console.WriteLine($"  Item: {item.Name}");
            }

            correctList = list;
            break;
        }
    }
}

if (correctList != null)
{
    var targetItem = correctList.FindFirstChild(cf => cf.ByName("India"));
    if (targetItem != null)
    {
        targetItem.Click();
        Console.WriteLine("Item 'India' selected.");
    }
    else
    {
        Console.WriteLine("'India' not found in dropdown.");
    }
}
else
{
    Console.WriteLine("No visible list with items was found.");
}
