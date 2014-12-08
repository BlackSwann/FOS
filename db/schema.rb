# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20141208133837) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "order_details", force: true do |t|
    t.float    "amount"
    t.integer  "order_id"
    t.integer  "product_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  add_index "order_details", ["order_id"], name: "index_order_details_on_order_id", using: :btree
  add_index "order_details", ["product_id"], name: "index_order_details_on_product_id", using: :btree

  create_table "order_states", force: true do |t|
    t.string   "name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "orders", force: true do |t|
    t.float    "final_price"
    t.integer  "order_state_id"
    t.datetime "created_at",     null: false
    t.datetime "updated_at",     null: false
    t.integer  "table_id"
  end

  add_index "orders", ["order_state_id"], name: "index_orders_on_order_state_id", using: :btree
  add_index "orders", ["table_id"], name: "index_orders_on_table_id", using: :btree

  create_table "product_types", force: true do |t|
    t.string   "name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "products", force: true do |t|
    t.string   "name"
    t.float    "price"
    t.integer  "product_type_id"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
  end

  add_index "products", ["product_type_id"], name: "index_products_on_product_type_id", using: :btree

  create_table "table_states", force: true do |t|
    t.string   "name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "tables", force: true do |t|
    t.integer  "number"
    t.integer  "table_state_id"
    t.datetime "created_at",     null: false
    t.datetime "updated_at",     null: false
  end

  add_index "tables", ["table_state_id"], name: "index_tables_on_table_state_id", using: :btree

end
