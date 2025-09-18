本タスクの目的
---
本タスクでは学習プランの「6.Edgeブラウザのコードの整理を伴う追加対応再挑戦（新運用マニュアル効果の確認を含む）」 の「STEP 2: SeleniumTest.java 修正」を実施する

今回の[タスク](https://github.com/uni-senbei/selenium-test-project/issues/3)  
※各AIの略称は[こちら](https://github.com/uni-senbei/selenium-test-project/blob/master/docs/AI%E3%83%81%E3%83%BC%E3%83%A0%E9%81%8B%E7%94%A8%E9%96%A2%E9%80%A3%E8%B3%87%E6%96%99/AI%E3%83%81%E3%83%BC%E3%83%A0%E3%83%A1%E3%83%B3%E3%83%90%E3%83%BC.md)  

## [SC-Dへの詳細設計書の作成依頼](https://github.com/uni-senbei/selenium-test-project/issues/3#issue-3337656396)
- タスク開始にあたってCMと協議
- 協議の中で詳細設計の粒度と形式について質問が出たため一番関係しているSC-Iに確認
- 依頼文作成前のSC-Dとの認識合わせの中で、設計構想ドキュメントが学習プランの内容を先取りする可能性があることについてどの方針で進めるかSC-Dからの質問
- SC-Iの意見、SC-Dの質問を反映した依頼文を作成しSC-Dに共有

## [STEP2 詳細設計ドラフト（SeleniumTest.java修正・先取り版）](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3205835431)
- SC-Dによる詳細設計ドラフト案の作成

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3208601821)
- CMによるレビュー結果

## [SC-Iのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3208631754)
- SC-Iによるレビュー結果
- 詳細設計ドラフト案をバッククォート3つで囲んでチャット欄に貼り付けたが3度やって3度とも囲んだ中身だけ見れなかったようなので、バッククォートで囲まずにドラフト案のみ貼り付けた所ようやく内容を確認できた

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3208643741)
- RV-Gによるレビュー結果

## [RV-Cレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3208661380)
- RV-Cによるレビュー結果
- このタイミングで2種類の回答が出たため念のため両方残した

## [SC-Dの対応方針](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3212650411)
- SC-Iの改善案と、RV-Cのレビュー結果にSC-Iの意見を統合した改善案に対するSC-Dの対応方針

## [CMとの協議結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3212707433)
- SC-Dの対応方針を基にCMと協議し、対応するもの、今回は見送るものを確定

## [SC-Iの見解を確認](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3213757489)
- 概ね許容だが2つの留意点あり

## [SC-Dへの最終指示書](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3213792726)
- SC-Iの見解を踏まえた調整後の最終指示書を作成

## [仮の詳細設計の確定版を作成](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3214088732)
- 最終指示書をSC-Dに共有し、確定版の作成を依頼
- SC-Dがドラフト案を踏襲できていない確定版を2度出力したため臨時でドキュメント生成専門のAIを用意
- ドキュメント生成専門のAIにはドラフト案と最終指示書以外の情報は共有していないため本AIチーム特有の前提が守られているかのレビューが必須
- SC-Dには上記視点でのレビュー実施を依頼
※無料版ChatGPTの制限がかかったため中断

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3217557291)
- CMによるレビュー結果は指摘点なし

## [SC-Iのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3217561435)
- SC-Iのレビュー結果は複数の指摘点あり
- 特に4点は修正必須として報告

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3217562375)
- RV-Gのレビュー結果は指摘点なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3217563139)
- RV-Cのレビュー結果は指摘点はあるもののこのままでも問題なし

## [SC-Dへの最終指示書](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3219714529)
- CM、SC-Iとの協議の結果、修正必須4件は今回対応、その他は次のステップで対応する事で方針を決定
- SC-Dへの修正指示を作成しSC-Dに修正の反映を依頼
- SC-Dは今回も元の詳細設計の確定版の文脈を保てなかったため、ドキュメント生成専門のAIに修正の反映を依頼

## [詳細設計の確定版](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3219860042)
- 臨時のドキュメント生成専門AIにより4件の修正を反映
- SC-Dによる差分とチーム特有の前提チェックを実施
- 問題ないとの事なので各レビュアーへレビュー依頼

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3222152296)
- CMによるレビュー結果は問題なし

## [SC-Iのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3222156363)
- SC-Iによるレビュー結果は問題なし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3222170461)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3222184811)
- RV-Cによるレビュー結果は問題なし

## 詳細設計フェーズは完了したため、実装フェーズを開始
- SeleniumTest.javaは肥大化していて一気に修正すると破綻する可能性があるため、以下の観点で段階的に修正する事を検討
  - 修正対象のSeleniumTest.javaに対し、具体的にどの要素をどのように修正するかリストアップ
  - 上記リストを基に修正の順序を策定
  - 必要に応じて疑似コードも作成

## [SeleniumTest.java 修正対象リスト（詳細版）](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3227399022)
- SC-Iが作成した **SeleniumTest.java（現状）** と **STEP2 詳細設計確定版** の照合結果
- なおSC-Iが最初に提示したものはCMの方で詳細度が足りないとの理由で却下

## [SC-Iからの「修正対象リスト」を基に、暫定的な優先度付けと安全かつ効率的な修正ロードマップの提案](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3227481332)
- SC-Iから、リストに順位を付けるだけではなく、依存関係・テスト容易性・安全性・重要度を加味した実装順序を提案

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3227498305)
- CMによるレビュー結果は問題なし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3227540829)
- RV-Gには簡単に経緯を説明した上でレビューを依頼し、結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3230517301)
- SC-Dによるレビューは概ね問題ないがいくつかの補足あり

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3230531018)
- RV-Cによるレビュー結果は問題ないがいくつかの改善ポイントあり

## [SC-Iの対応方針](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3236539035)
- 各レビュアーのレビュー結果を受けてCMと協議し、SC-Dからあがった3点を修正候補としてピックアップ
- 修正候補3点をSC-Iに共有し、対応方針の提出を依頼

## [SC-IによるTestResult仕様案](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3236659105)
- SC-Iの対応方針を受けて、CM、SC-Dと協議の結果、TestResultの仕様確定を優先させることで合意
- SC-IにTestResult仕様の確定案作成を依頼

## [SC-Iによる修正ロードマップの改訂](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3239175309)
- SC-Iによる修正ロードマップの改訂

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3239198757)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3239199754)
- SC-Dによるレビュー結果は改善提案あり

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3239200610)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3239201255)
- RV-Cによるレビュー結果は若干の確認ポイントはあるものの問題なし

## [SC-Iの下準備具体化](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3240583253)
- SC-Iによる「修正ロードマップのSTEP0下準備」でやる事の具体化
- v2となっているのは、初稿はSeleniumTest.javaの内容を踏襲できていなかったため
- また、SC-IからはSeleniumTest.javaをどう修正するかの提案が主だったが、既存のSeleniumTest.javaはリネームして新規でSeleniumTest.javaを作成し直すことを提案

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3244544128)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3244577047)
- SC-Dによるレビュー結果ではいくつかの指摘点があるが、「TestResult仕様の未確定項目」について確認したところ、「未確定」と触れたのは誤りと報告

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3244590700)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3244595123)
- RV-Cによるレビュー結果はいくつかの改善提案はあるが問題はなし

## STEP 0: 下準備の開始
* 0-1. SeleniumTest.java のベースライン確認を開始
- テスト成功時のスクリーンショットについてはSC-Iとの協議の結果今回は見送り
- SeleniumTest.java を SeleniumTest_legacy.java にリネーム
- SeleniumTest_legacy.java のテスト実行はSeleniumTestManager.java との連携が解除されなかったため失敗
- SeleniumTest_legacy.java をプロジェクト管理外のフォルダへ移動し、SeleniumTest.java を作成
* 0-2. 簡易テストケースの準備を開始
- SC-IによるSeleniumTest.java のコード生成

## [SC-I作成のSeleniumTest.javaに書く最小限の正常系テスト](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3251192292)
- SC-Iによるコード生成
- 実際にSeleniumTest.javaに貼り付けてみると多数のエラーが出ているが、まずはそのことは伝えず各レビュアーAIのレビューを依頼
* エラーについて
- jupiterがエラーが出ている
  - jupiterを調べた所JUnit5で使うものと出ていたが、今まではJUnit4で進行していた認識なのでその影響と思われる
  - @testなどの全アノテーションが赤文字になっているが、これはJupiterを使っているからと推測
  - その他getDriverやopen、close、assertEquals、assertTrueも赤文字になっているが、これも同じ原因ではないかと推測

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3251295156)
- CMによるレビュー結果は問題なし

## [SC-D視点でのレビュー](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3251313428)
- SC-Dによるレビュー結果はいくつかの改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3251323781)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3251332247)
- RV-Cによるレビュー結果はいくつかの改善案はあるものの問題はなし

## [SC-IによるJUnit4 対応](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3257829103)
- CMにエラー内容を共有し原因を調査
- エラーの原因は2点あり、JUnit5を使っている事の他にSeleniumTestManagerで未対応のメソッドがある事
- JUnitについては4で進めていたので、今回はコードをJUnit4に合わせて修正

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3257839933)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3257868491)
- SC-Dによるレビュー結果はいくつかの改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3257875134)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3257881334)
- RV-Cによるレビュー結果はいくつかの改善案はあるものの問題なし

## [SC-IによるSTEP1の具体化](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3263317743)
- ChatGPTのどちらの回答が好みかの選択肢が出たため回答は2つ
- 各AIにどちらの回答がいいか確認したところ、回答2を推奨していたので、回答2をベースにレビューを依頼

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3263336307)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3263337816)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3263338462)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3263340990)
- RV-Cによるレビュー結果は改善案はあるものの問題なし

## [STEP1-1: configure/setUp の統一（実装具体化）](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3268492346)
- SC-Iによるconfigure/setUp の統一案

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3268495092)
- CMによるレビュー結果は問題なし

## [SC-D 視点（設計寄り）のレビュー](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3268499743)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3268505553)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3268510226)
- RV-Cによるレビュー結果は改善案はあるものの問題はなし

## [CMからの修正依頼](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3268539419)
- 各レビュアーのレビュー結果を受けてCMと協議した結果、以下2点は反映する事で合意
  - SeleniumTestManagerから@Beforeアノテーションを外す
  - 未実装メソッドの追加

## [SC-Iによる修正版の作成](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3272870913)
- SC-Iにより2点の修正版を作成

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3272874600)
- CMによるレビュー結果は問題なし

## [SC-D視点でのレビュー](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3272876554)
- SC-Dによるレビュー結果は改善提案はあるが、問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3272878091)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3272879640)
- RV-Cによるレビュー結果は改善提案はあるが、問題はなし

## [SC-Iによるコード生成](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3274349037)
- 各レビュアーからは次の1-2へ進むようコメントがあったが、SC-IとしてはSeleniumTestManager.javaの仕様確定を優先したいとの事だったので、コード生成を依頼
- IllegalStateExceptionについては、再度組み込むよう依頼

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3276972423)
- CMによるレビュー結果は問題なし

## [SC-D視点でのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3276976344)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3276977945)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3276980607)
- RV-Cによるレビュー結果は改善案はあるものの問題はなし

## [SeleniumTestManager.javaに修正を適用](https://github.com/uni-senbei/selenium-test-project/blob/master/src/test/java/com/example/test/SeleniumTestManager.java)
- SeleniumTestManager.javaに修正したコードを適用
- 現状ではTestConfig クラスが未実装のため、TestConfig、createDriver、getBaseUrlの3つは未解決のまま
- 想定外のエラーなどは出ていないため、STEP1-1はこれで完了

## [STEP1-2 のレビュアー確認用ドキュメント（具体化案） まとめ](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3283069139)
- SC-IにSTEP1-2 のドキュメント（具体化案） まとめを依頼

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3283072406)
- CMによるレビュー結果は問題なし

## [SC-D 視点でのレビュー](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3283089555)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3283093636)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3283098069)
- RV-Cによるレビュー結果は改善案はあるものの問題はなし

## [レビュー結果と改善提案をすべて反映した **STEP1-2: 確定版ドキュメント** を作成](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3284962684)
- ドキュメント確定版に以下の反映を依頼
   * `close()` は `driver.quit()` + `driver=null`
   * `open()` は相対/絶対URL両対応、結合処理は整形（ユーティリティ化 or 末尾スラッシュルール）
   * `TestConfig` コンストラクタでディレクトリ存在チェック追加
   * コメントで「quit を選んだ理由」「将来の拡張点」明記

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3287347214)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3287349616)
- SC-Dによるレビュー結果は改善案はあるものの問題なし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3287350815)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3287353469)
- RV-Cによるレビュー結果は改善案はあるものの問題なし

## [TestConfig.java の設計図レベル擬似コード作成](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3291158494)
- SC-Iによる疑似コード作成

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3291176109)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3291204352)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3291216616)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3291244975)
- RV-Cによるレビュー結果は改善案はあるものの問題はなし

## [レビューで挙がった改善案を反映した設計図](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3295228472)
- SC-Iにレビューで挙がった改善案の反映を依頼

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3295250092)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3295305563)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3295319365)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3295336108)
- RV-Cによるレビュー結果は改善案はあるものの問題はなし

## [完成版の擬似コード](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3297063631)
- 改善提案を受けて以下を反映
  - normalizeUrl() の正規表現を安全化
  - 設定ファイル読み込み時の null チェック追加
  - 例外時に最低限のメッセージを出力
  - デフォルト値（base.url / screenshot.dir）を用意し、config がなくても動作可能

## [擬似コードをベースに、TestConfig.java の実装コードを生成](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3300891615)
- 以下のレビューで多数の改善提案がでたものの、現状のコード自体には問題がないとの事なので一旦実装し、現在残っているコンパイルエラーが解消されたことを確認

## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3302134612)
- CMによるレビュー結果は改善案はあるものの問題はなし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3302139375)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3302146080)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3302154132)
- RV-Cによるレビュー結果は改善案はあるものの問題はなし

## [レビュー結果の整理](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3305148385)
- SC-Iにレビュー結果を共有し、対応する/しない/相談したいに分類を依頼

## [CMを交えた協議結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3305157589)
- SC-Iの修正方針をCMと協議し、その結果をSC-Iに共有、実装方針を確定

## [修正版Test Config](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3306397356)
- 協議結果をTestConfig.javaに反映
- なおこの際、SC-Iからレビュー観点に以下の観点を加えるよう依頼
* `normalizeUrl()` が意図通り動作するか（特に末尾スラッシュの有無）
* `screenshotDir` のパス変換とディレクトリ作成のタイミングが妥当か
* `baseUrl` 空チェックでの例外メッセージが分かりやすいか
* 既存コード（SeleniumTestManagerなど）に影響が出ないか


## [CMのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3306483989)
- CMによるレビュー結果は問題なし

## [SC-Dのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3306501028)
- SC-Dによるレビュー結果は改善案はあるものの問題はなし

## [RV-Gのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3306508364)
- RV-Gによるレビュー結果は問題なし

## [RV-Cのレビュー結果](https://github.com/uni-senbei/selenium-test-project/issues/3#issuecomment-3306512636)
- RV-Cによるレビュー結果は改善案はあるものの問題はなし

## 次の作業
- SC-Iにレビュー結果を共有し、対応方針を協議予定
