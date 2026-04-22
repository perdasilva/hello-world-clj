.PHONY: run test clean help

help: ## Show this help
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  %-15s %s\n", $$1, $$2}'

run: ## Run the app (use NAME= for a custom greeting)
	clj -M -m hello.core $(NAME)

test: ## Run all tests (example-based and generative)
	clj -M:test

clean: ## Remove build caches
	rm -rf .cpcache target
