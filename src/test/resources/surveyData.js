// アンケートデータをJSON形式で定義
const surveyData = {
    interests: [
        { "value": "politics", "label": "政治とタイミング", "default_selected": false },
        { "value": "strategy", "label": "戦略と勢い", "default_selected": true },
        { "value": "culture", "label": "文化と歴史", "default_selected": false }
    ],
    gender: [
        { "value": "male", "label": "男性", "id": "radio-a" },
        { "value": "female", "label": "女性", "id": "radio-b" }
    ],
    favoriteMS: [
        { "value": "シャアザク", "label": "シャアザク" },
        { "value": "グフ", "label": "グフ" },
        { "value": "ドム", "label": "ドム" },
        { "value": "ゲルググ", "label": "ゲルググ" },
        { "value": "アッガイ", "label": "アッガイ" } // input.htmlにアッガイが追加されているため含める
    ]
};