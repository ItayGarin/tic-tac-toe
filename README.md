# Tic Tac Toe

## Overview

This is an implementation of the classic Tic Tac Toe game using the Clojurescript ecosystem.
Specifically, it is utilizing the awesome [reagent](https://github.com/reagent-project/reagent)/[re-frame](https://github.com/Day8/re-frame) combo :heart:!

I strongly encourage you to read [re-frame](https://github.com/Day8/re-frame/blob/master/README.md)'s README.
It is truly magnificent :sparkles:.

This project is my way of trying out these different technologies.
Moreover, currently, this project only operates at the frontend.

## Dependencies

These are the projects / libs / technologies that this game uses.

- [Clojurescript](https://github.com/clojure/clojurescript)
- [Boot](https://github.com/boot-clj/boot)
- [Reagent](https://github.com/reagent-project/reagent)
- [re-frame](https://github.com/Day8/re-frame)
- [Sketch 3D Font](http://www.dafont.com/sketch-3d.font)
- [Tenzing](https://github.com/martinklepsch/tenzing) / [Tenzing re-frame](https://github.com/daslu/tenzing-re-frame-todomvc)

## Usage

### Development / Server

This project makes use of the wonderful [boot](https://github.com/boot-clj/boot) build system.
It so flexible and pleasant to work with. I'd recommend you try it out for yourself!

The `dev` task facilitates Bret Victor's vision of the immediate feedback development environment.
It uses `boot-reload` to recompile and push change to the browser in real-time.
i.e There's no need to reload the page. Simply save in your editor, and the changes will auto-magically be pushed to the browser.

```
cd tic-tac-toe/
boot dev
```

### TDD

The `auto-test` target let's automatically watches and re-runs tests.
Boot makes these kind of tasks quite trivial.

```
cd tic-tac-toe/
boot auto-test
```

Note that if you just want to run the tests once, you could use the `test` task.

### Picture

![tic-tac-toe](https://raw.github.com/thifi/tic-tac-toe/master/resources/tic-tac-toe.png)

## License

The MIT License (MIT)

Copyright (c) 2016 Itay Garin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
