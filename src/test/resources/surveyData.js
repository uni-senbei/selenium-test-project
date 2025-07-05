// アンケートデータをJSON形式で定義
const surveyData = {
    interests: [
        { "value": "politics", "label": "政治とタイミング", "default_selected": false },
        { "value": "strategy", "label": "戦略と勢い", "default_selected": true },
        { "value": "culture", "label": "文化と歴史", "default_selected": false },
        { "value": "economy", "label": "経済と未来", "default_selected": false }
    ],
    gender: [
        { "value": "male", "label": "メンズ", "id": "radio-a" },
        { "value": "female", "label": "レディース", "id": "radio-b" },
        { "value": "no_answer", "label": "答えない", "id": "radio-c" }  // 最後のカンマは任意
    ],

    favoriteMS: [
        { "value": "シャアザク", "label": "シャアザク" },
        { "value": "グフ", "label": "グフ" },
        { "value": "ドム", "label": "ドム" },
        { "value": "ゲルググ", "label": "ゲルググ" },
        { "value": "アッガイ", "label": "アッガイ" } // input.htmlにアッガイが追加されているため含める
    ]
};