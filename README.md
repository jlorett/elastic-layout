# ElasticLayout

ElasticLayout is a ConstraintLayout with an elastic scroll effect.

<img src="./readme-assets/showcase.gif" width="40%"/>

## Layout

The layout has an **elasticity** attribute (float) that controls how easy the layout scrolls. More friction is applied to the scroll as this value gets smaller. It also has a **threshold** attribute (dimension) to setup how far a user must drag to dismiss the layout. **ElasticLayout requires a child NestedScrollView or RecyclerView to properly work.**

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.joshualorett.elasticlayout.ElasticLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:threshold="56dp"
    app:elasticity="0.4">
    <androidx.core.widget.NestedScrollView
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

    </androidx.core.widget.NestedScrollView>
</com.joshualorett.elasticlayout.ElasticLayout>
```

## Listeners

### DismissListener

Listens for when an ElasticLayout has been dragged and released past its threshold. This can be used to finish an activity or remove a fragment.

### DragThresholdListener

Listens for when an ElasticLayout was dragged past its threshold. This can be used to indicate to the user when a layout is in a dismissible state. For example, you could perform haptic feedback.
