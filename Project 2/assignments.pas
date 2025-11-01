program Assignments;

uses StrUtils, sysutils;

(* this procedure displays all the pending assignments *)
procedure GetPendingAssignments;
	var
		inFile: Text;
		line: String;
		class, assignment, due, status: String;
		id, av_points, assignment_count: Integer;
		row_num: Integer = -1;

	begin

		Assign(inFile, 'assignments.csv');
		Reset(inFile);
		assignment_count := 0;
		
		repeat
			Readln(inFile, line);

			row_num := row_num + 1;
			if row_num = 0 then continue;
			
			id := StrToInt(ExtractDelimited(1, line, [',']));
			class := ExtractDelimited(2, line, [',']);
			assignment := ExtractDelimited(3, line, [',']);
			due := ExtractDelimited(4, line, [',']);
			status := ExtractDelimited(5, line, [',']);
			av_points := StrToInt(ExtractDelimited(6, line, [',']));
			
			if status = 'Completed' then continue;
			assignment_count := assignment_count + 1;			

			writeln('-----------------------');
			writeln('Assignment ID: ', id);
			writeln('Class: ', class);
			writeln('Assignment: ', assignment);
			writeln('Due: ', due);
			writeln('Max Points: ', av_points);
			
		until EOF(inFile);

		Close(inFile);

		writeln('');
		writeln('Total Pending Assignments: ', assignment_count);

	end;

(* this function returns the next id for new record to be inserted *)
function GetNextId() : Integer;
	var 
		inFile: Text;
		line: String;
		id: Integer = 0;
		row_num: Integer = -1;

	begin
		Assign(inFile, 'assignments.csv');
                Reset(inFile);

		repeat
                        Readln(inFile, line);

                        row_num := row_num + 1;
                        if row_num = 0 then continue;

                        id := StrToInt(ExtractDelimited(1, line, [',']));
		until EOF(inFile);

		Close(inFile);
		GetNextId := id + 1;		
	end;

(* this procedure allows user to input new assignment *)
procedure AddAssignment;
	type
		ThisAssignment = record
			id, av_points, my_points: Integer;
			class, assignment, due, status: String;
		end;

	var
		new_assignment: ThisAssignment;
		outFile: Text;

	begin
		Assign(outFile, 'assignments.csv');
		Append(outFile);

		write('Class Name: ');
		readln(new_assignment.class);
		write('Assignment Name: ');
		readln(new_assignment.assignment);
		write('Due Date: ');
		readln(new_assignment.due);
		write('Max Possible Points: ');
		readln(new_assignment.av_points);
		
		new_assignment.id := GetNextID;
		new_assignment.my_points := 0;
		new_assignment.status := 'Pending';
		writeln(outFile, new_assignment.id, ',', UpperCase(new_assignment.class), ',', new_assignment.assignment, ',', new_assignment.due, ',', new_assignment.status, ',', new_assignment.av_points, ',', new_assignment.my_points);
		
		Close(outFile);
	
	end;

(* thsi procedure takes input by reference and increments the variable directly by given value *)
procedure IncrementVariable(var variable: Integer; value: Integer);
	begin
		variable := variable + value;
	end;

(* this procedure calculates the grade of student for certain class and displays *)
procedure GetClassStats();
	var
		inFile: Text;
                line, in_class, class, status: String;
                av_points, my_points: Integer;
                row_num: Integer = -1;
		pending_assignments: Integer = 0;
		completed_assignments: Integer = 0;
		my_total_points: Integer = 0; 
		available_total_points: Integer = 0;
		grade: Extended = 0.0;

	begin
		write('Enter Class Name: ');
		readln(in_class);
		in_class := UpperCase(in_class);
		Assign(inFile, 'assignments.csv');
                Reset(inFile);

		writeln('');

		repeat
                        Readln(inFile, line);

                        row_num := row_num + 1;
                        if row_num = 0 then continue;

                        class := ExtractDelimited(2, line, [',']);
                        if class <> in_class then continue;

			status := ExtractDelimited(5, line, [',']);
                        av_points := StrToInt(ExtractDelimited(6, line, [',']));
                        my_points := StrToInt(ExtractDelimited(7, line, [',']));
			
			if status = 'Completed' then
				begin
					IncrementVariable(completed_assignments, 1);
					IncrementVariable(my_total_points, my_points);
					IncrementVariable(available_total_points, av_points);
				end
			else if status = 'Pending' then
				begin
					IncrementVariable(pending_assignments, 1);
				end;


		until EOF(inFile);

                Close(inFile);		
		
		writeln('');
		if (pending_assignments = 0) and (completed_assignments = 0) then writeln('No such class found!')
		else
			begin
				writeln('Statistics for ', in_class);
				writeln('Completed Assignments: ', completed_assignments);
				writeln('Pending Assignments: ', pending_assignments);
				if (my_total_points > 0) then grade := (my_total_points/available_total_points)*100;
				writeln('Grade: ', grade:0:2, '%');
			end
	end;

(* this procedure finds the id of completed and writes in temp file and copies all of it to original file *)
procedure MarkAsCompleted();
	var 
		inFile, oldFile, tempOutFile: Text;
                line, oldLine, status, id, class, assignment: String;
		row_num: Integer = -1;
		old_row_num: Integer = -1;
		in_id, in_score, cmpr_id: Integer;

	begin
		Assign(inFile, 'assignments.csv');
                Reset(inFile);

		writeln('Pending Assignments: ');
		repeat

                        Readln(inFile, line);
	
                        row_num := row_num + 1;
                        if row_num = 0 then continue;

                        status := ExtractDelimited(5, line, [',']);
			if status = 'Completed' then continue;

			id := ExtractDelimited(1, line, [',']);
			class := ExtractDelimited(2, line, [',']);
                        assignment := ExtractDelimited(3, line, [',']);

			writeln('ID: ', id, ' | Class: ', class, ' | Assignment: ', assignment);

		until EOF(inFile);

                Close(inFile);

		writeln('');
		write('ID of the assignment: ');
		readln(in_id);
		write('Your score for the assignment: ');
		readln(in_score);

		Assign(oldFile, 'assignments.csv');
		Assign(tempOutFile, 'temp_assignments.csv');
		Reset(oldFile);
		Rewrite(tempOutFile);
		writeln(tempOutFile, 'ID,Class,Assignment,Due,Status,PossiblePoints,MyPoints');

		repeat

			Readln(oldFile, oldLine);

			old_row_num := old_row_num + 1;
                        if old_row_num = 0 then continue;

			cmpr_id := StrToInt(ExtractDelimited(1, oldLine, [',']));
			if cmpr_id = in_id then
				begin
					writeln(tempOutFile, cmpr_id, ',', ExtractDelimited(2, oldLine, [',']), ',', ExtractDelimited(3, oldLine, [',']), ',', ExtractDelimited(4, oldLine, [',']), ',Completed,', ExtractDelimited(6, oldLine, [',']), ',', in_score);
					continue;
				end;
			
			writeln(tempOutFile, oldLine);


		until EOF(oldFile);
		
		Close(oldFile);
		Close(tempOutFile);

		Assign(tempOutFile, 'temp_assignments.csv');
		Assign(oldFile, 'assignments.csv');
		Reset(tempOutFile);
                Rewrite(oldFile);

		repeat
                        Readln(tempOutFile, oldLine);
                        writeln(oldFile, oldLine);
                until EOF(tempOutFile);
		Close(oldFile);
                Close(tempOutFile);
	end;

(* this is the option being displayed to the user *)
procedure SelectOption(option: Integer);
        begin
		if option = 0 then writeln('Terminated!')
                else if option = 1 then GetPendingAssignments
                else if option = 2 then AddAssignment
		else if option = 3 then GetClassStats
		else if option = 4 then MarkAsCompleted
                else writeln('Invalid Option Selected!');
        end;

(* MAIN PROGRAM STARTS HERE *)

var
	user_option: Integer = -1;

begin
	while user_option <> 0 do
		begin
			writeln('');
			writeln('========== MAIN MENU ==========');
			writeln('0: Terminate Program');
			writeln('1: Pending Assignments');
			writeln('2: Add New Assignment');
			writeln('3: Get Class Statistics');
			writeln('4: Mark Assignment Complete');
			writeln('');
			write('Enter Your Option: ');
			readln(user_option);
			writeln('');
			SelectOption(user_option);
			writeln('');
		end;

end.
