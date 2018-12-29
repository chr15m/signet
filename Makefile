LUMO=./node_modules/.bin/lumo -c src
TARGETS=stash.js

scripts: $(TARGETS)

%.js: src/signet/*.cljs node_modules
	$(LUMO) build.cljs $@

test: node_modules
	@$(LUMO) ./src/signet/tests.cljs

node_modules:
	npm i

clean:
	rm -f $(TARGETS)
