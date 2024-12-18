<%-- 
    Document   : maklumat_pemilik22
    Created on : Jul 20, 2010, 11:38:39 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function save(event, f){

        var namaProjek = document.form1.namaProjek.value;
        var namaSkim = document.form1.namaSkim.value;
        var namaPemilik = document.form1.namaPemilik.value;
        var alamat = document.form1.alamat.value;
        var poskod = document.form1.poskod.value;
        var negeri = document.form1.negeri.value;
        var kosRendah = document.form1.kosRendah.value;


        if ((namaProjek == ""))
        {
            alert('Sila masukkan Nama Projek ');
            document.form1.namaProjek.focus();
        }
       else if ((namaSkim == ""))
        {
            alert('Sila masukkan Nama Skim ');
            document.form1.namaSkim.focus();
        }
        else if ((namaPemilik == ""))
        {
            alert('Sila masukkan Nama Pemilik ');
            document.form1.namaPemilik.focus();
        }
        else if ((alamat == ""))
        {
            alert('Sila masukkan Alamat ');
            document.form1.alamat.focus();
        }
        else if ((poskod == ""))
        {
            alert('Sila masukkan Poskod ');
            document.form1.poskod.focus();
        }
        else if ((negeri == ""))
        {
            alert('Sila pilih Negeri ');
            document.form1.negeri.focus();
        }

        else if ((kosRendah == ""))
        {
            alert('Sila pilih jenis Kos rendah ');
            document.form1.kosRendah.focus();
        }

        else
        {

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');



        }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.strata.RMHSPermohonanStrataActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <c:if test="${edit123}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Kemasukan Maklumat Pemilik</legend>
                <p>
                    <label>Nama Projek :</label><s:text name="pemilik.nama" size="30" id="namaProjek"/><em>*</em>

                </p>

                <p>
                    <label>Nama Skim :</label><s:text name="pemilik.namaSkim" size="30" id="namaSkim"/><em>*</em>

                </p>
                <p>
                    <label>Nama Pemilik : </label> <s:text name="pemilik.pemilikNama" size="30" id="namaPemilik"/><em>*</em>

                </p>
                <p>
                    <label>Alamat Berdaftar :</label>
                    <s:text name="pemilik.pemilikAlamat1" size="30" id="alamat"/><em>*</em>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pemilik.pemilikAlamat2" size="30" />
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pemilik.pemilikAlamat3" size="30"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pemilik.pemilikAlamat4" size="30" />
                <p>
                    <label>Poskod :</label>
                    <s:text name="pemilik.poskod" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/><em>*</em>
                </p>

                <p>
                    <label>Negeri :</label>
                    <s:select name="negeri" value="${actionBean.pemilik.negeri.kod}" id="negeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select><em>*</em>
                </p>
                <p>
                    <label>Jenis Kos Rendah :</label><s:radio name="pemilik.kosRendah" id="kosRendah" value="Y"></s:radio> Ya
                    <s:radio name="pemilik.kosRendah" value="N"></s:radio> Tidak
                </p>
                <p>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.pemilik eq null}">
                        <s:button name="simpanPemilik" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                    </c:if>

                    <c:if test="${actionBean.pemilik ne null}">
                        <s:button name="updatePemilik" value="Kemaskini" class="btn" onclick="save(this.name, this.form);"/>
                    </c:if>
                </p>

            </fieldset>
        </div>
    </c:if>
    </s:form>