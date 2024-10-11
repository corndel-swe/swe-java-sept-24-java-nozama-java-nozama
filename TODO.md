# Workshops

> [!NOTE]
>
> Make sure to read `CONTRIBUTING.md` to see how to work on Nozama.

## Day 1

Focus on completing the model layer for the application.

Choose an item from the Trello board move it to doing, so your teammates know
which items are being worked on. You can also assign the card to yourself.

Each item has a checklist. You won't be able to fully complete any cards today
because they involve adding API endpoints, which we'll be learning about
tomorrow. However, once you're done with the model layer of your chosen card,
you can move the card to "Blocked" and pick up a new one.

## Day 2

We're ready to start adding endpoints to our application.

Follow the specification in the
[Nozama API Documentation](https://nozama-api.netlify.app/).

You probably have a few cards in the "Blocked" column. Today, you'll be
finishing them off. Take a card from "Blocked" and put it in "Doing".

To manually test your endpoints, use a client such as
[Thunderclient](vscode:extension/rangav.vscode-thunder-client).

When you're done with a card, use the "Review" and "Done" columns to help
organise your teamwork.

## Day 3

This workshop is all about refactoring to improve our app. Based on what you've
learned today, feel free to add new cards to the backlog and then follow the
same process as the previous days to complete them.

Some refactors you should consider:

- Organising the API with [routers and controllers](https://tech-docs.corndel.com/javalin/routing.html)

- Using custom
  [errors and error handling](https://tech-docs.corndel.com/javalin/sending-errors.html)

- Adding
  [validation to the API](https://tech-docs.corndel.com/javalin/schema-validation.html).
  Note that some schemas are already written in `spec/schemas.js`.

- Adding new endpoints to the API

- Adding more test coverage
