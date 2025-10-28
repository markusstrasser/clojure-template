# Skills

Filesystem-based workflows with progressive disclosure for AI agents.

## Structure

Each skill follows this pattern:

```
skills/<skill-name>/
  SKILL.md          # L1: Metadata (name, description, triggers)
  run.sh            # Main orchestration script
  data/             # Skill-specific data files
  examples/         # Usage examples
```

## Creating a Skill

1. Create directory: `mkdir skills/my-skill`
2. Add `SKILL.md` with frontmatter:
   ```markdown
   ---
   name: My Skill Name
   description: Brief description. Triggers: keywords, that, activate, this, skill. Requires: tools, env vars.
   ---

   # My Skill Name

   ## Prerequisites
   ...

   ## Quick Start
   ...
   ```
3. Add `run.sh` executable script
4. Document in CLAUDE.md

## Example Skills

See the `evo` project for example skills:
- Code research workflows
- Diagnostics and health checks
- Session memory search
- Architectural decision workflows
