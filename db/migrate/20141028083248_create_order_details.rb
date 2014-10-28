class CreateOrderDetails < ActiveRecord::Migration
  def change
    create_table :order_details do |t|
      t.float :amount
      t.references :order, index: true
      t.references :product, index: true

      t.timestamps null: false
    end
  end
end
