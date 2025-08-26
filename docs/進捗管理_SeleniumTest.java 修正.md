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

## 次の作業
- 詳細設計の確定版を基にコード修正を開始予定
