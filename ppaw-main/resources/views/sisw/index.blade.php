@extends('template')

@section('content')
    <div class="row mt-5 mb-5">
        <div class="col-lg-12 margin-tb">
            <div class="float-left">
                <h2>CRUD LARAVEL </h2>
            </div>
            <div class="float-right">
                <a class="btn btn-success" href="{{ route('sisw.create') }}"> Input Siswa</a>
            </div>
        </div>
    </div>

    @if ($message = Session::get('succes'))
    <div class="alert alert-success">
        <p>{{ $message }}</p>
    </div>
    @endif

    <table class="table table-bordered">
        <tr>
            <th width="20px" class="text-center">No</th>
            <th width="280px"class="text-center">No PEND</th>
            <th width="280px"class="text-center">Nama</th>
            <th width="280px"class="text-center">BHS iND</th>
            <th width="280px"class="text-center">BHS IpA</th>
            <th width="280px"class="text-center">BHS MTK</th>
            <th width="280px"class="text-center">TOTAl</th>
        </tr>
        @foreach ($sisw as $siswa)
        <tr>
            <td class="text-center">{{ ++$i }}</td>
            <td>{{ $siswa->no_pendaftaran }}</td>
            <td>{{ $siswa->nama }}</td>
            <td>{{ $siswa->nilai_ind }}</td>
            <td>{{ $siswa->nilai_ipa }}</td>
            <td>{{ $siswa->nilai_mtk }}</td>

            <td class="text-center">
                <form action="{{ route('sisw.destroy',$siswa->id) }}" method="POST">

                   <a class="btn btn-info btn-sm" href="{{ route('sisw.show',$siswa->id) }}">Show</a>

                    <a class="btn btn-primary btn-sm" href="{{ route('sisw.edit',$siswa->id) }}">Edit</a>

                    @csrf
                    @method('DELETE')

                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus data ini?')">Delete</button>
                </form>
            </td>
        </tr>
        @endforeach
    </table>

    {!! $sisw->links() !!}

@endsection