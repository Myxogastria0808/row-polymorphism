class parent (x0 : int) =
  object
    val x = x0
    method get_x = x
  end

class child (x0 : int) (y0 : int) =
  object
    inherit parent x0
    val y = y0
    method get_y = y
  end

(** Javaの情報欠損するパターン1に対応するもの **)
let printXY (human : < get_x : int ; .. >) =
  print_endline ("x: " ^ string_of_int human#get_x);
  (* 引数の型の要件じょりも厳しい要件のはずなのに、以下は問題なく実行できる *)
  (* get_y : int というメソッドを持つclassが引数に入っても、printXY内で問題なく使用できる -> データ欠損がないことを部分的に示している。 *)
  print_endline ("y: " ^ string_of_int human#get_y)

let () =
  let parent = new parent 0 in
  let child = new child 1 1 in

  (* printXYでは、parent class が条件を満たしているにも関わらず、parent classのインスタンスを引数に取れない *)
  (* 以下は、エラーと判定される *)
  (* printXY parent; *)

  (* getY() メソッドも呼び出すことができる *)
  printXY child

(** Javaの情報欠損するパターン2に対応するもの **)
let throwClass (human : < get_x : int ; .. >) : < get_x : int ; .. > = human

let () =
  let parent = new parent 0 in
  let child = new child 1 1 in

  (* throwClassでは、parent class が条件を満たしているので、parent classのインスタンスを引数に取れる *)
  (* 以下は、エラーと判定されない *)
  let returned_parent = throwClass parent in
  print_endline ("Returned parent x: " ^ string_of_int returned_parent#get_x);

  (* child classも条件を満たしているので、child classのインスタンスも引数に取れる *)
  (* 以下は、エラーと判定されない *)
  let returned_child = throwClass child in
  print_endline ("Returned child x: " ^ string_of_int returned_child#get_x);
  (* get_y : int が欠損することなく呼び出せている *)
  print_endline ("Returned child y: " ^ string_of_int returned_child#get_y)
