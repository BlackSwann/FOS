class CreateOrders < ActiveRecord::Migration
  def change
    create_table :orders do |t|
      t.float :final_price
      t.references :order_state, index: true

      t.timestamps null: false
    end
  end
end
