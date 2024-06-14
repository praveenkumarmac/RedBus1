Feature: TrainBooking

Scenario: Book a train in RedBus application
Given Launch the browser and appilication
When User clicks on redRail
And User enters the value in from place
And User enters the value in to place
And User selects a data  in the Date DropDown
And User selects a free cancellation
And User clicks on search button
Then Validate the train displayed in the UI
And validate the trains displayed as per given value

