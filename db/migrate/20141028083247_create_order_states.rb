class CreateOrderStates < ActiveRecord::Migration
  def change
    create_table :order_states do |t|
      t.string :name

      t.timestamps null: false
    end
  end
end
