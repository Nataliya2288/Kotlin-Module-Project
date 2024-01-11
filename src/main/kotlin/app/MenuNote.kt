package app

class MenuNote (private val archive: Archive) {
    fun menuNotes() { // функция меню Заметок
        val countListNote =
            archive.listOfNotes.count() + 1 // количество объектов в списке Заметок +1 - для получения номера пункта Выход и проверки вводимых данных

        println("\nАрхив «${archive.nameArchive}»")
        println("Список заметок:")
        println("0. Создать заметку")


        // Проходим по листу заметок, если объекты есть печатаем их пункты как индекс +1, так как первый пункт меню постоянный
        archive.listOfNotes.forEachIndexed { index, arihive -> println("${index + 1}. ${arihive.nameNote}") }
        println("$countListNote. Назад в список архивов ") // Пункт  постоянный, считает объекты в списке и выводит пункт как  +1

        // проверяем ввод данных для выбора пункта на ошибки через checkInput и исходя из пункта запускаем следущие функции
        val noteMenuItem = checkInput(
            countListNote,
            "Введите число от 0 до ",
            "Такого пункта нет\nВведите число от 0 до "
        )

        when (noteMenuItem) {
            0 -> createNote() // функция создания заметки
            // создаем класс Вид Заметки с передачей объекта Архива и выбранного объекта Заметки и запускаем функцию Вид заметки
            in 1..archive.listOfNotes.count() -> ViewNote(
                archive,
                archive.listOfNotes[noteMenuItem - 1]
            ).viewNote()
            countListNote -> MenuArchive().menuArchive() // возвращаемся в меню Архивов
        }

    }


    private fun createNote() {
        print("\nВведите название заметки: > ")
        val newNameNote = readln().trim() // Удаляем  пробелы
        if (newNameNote.isEmpty()) {
            println("Ошибка: название заметки не может быть пустым")
            menuNotes()
            return
        }
        print("\nВведите текст заметки: > ")
        val textNote = readln().trim()
        if (textNote.isEmpty()) {
            println("Ошибка: заметка не может быть пустой")
            menuNotes()
            return
        }
        archive.listOfNotes.add(
            Note(
                newNameNote,
                textNote
            )
        ) // добавляем Заметку в лист Заметок
        println("Заметка $newNameNote создана")
        menuNotes() // возвращаемся в меню Заметок
    }
}