<%--
    Document   : testing1
    Created on : July 23, 2010, 10:34:01 AM
    Author     : Sreenivasa Reddy Munagal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


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
        var pengurusanNama = document.form1.pengurusanNama.value;
        var pengurusanNoSijil = document.form1.pengurusanNoSijil.value;
        var jenisP = document.form1.jenisP.value;
        var pengurusanNoPengenalan = document.form1.pengurusanNoPengenalan.value;
        var pengurusanAlamat1 = document.form1.pengurusanAlamat1.value;
        var pengurusanPoskod = document.form1.pengurusanPoskod.value;
        var pkodNegeri = document.form1.pkodNegeri.value;
        

        if ((pengurusanNama == ""))
        {
            alert('Sila masukkan Nama Perbadanan Pengurusan ');
            document.form1.pengurusanNama.focus();
        }
        else if ((pengurusanNoSijil == ""))
        {
            alert('Sila masukkan Nombor Sijil Perbadanan Pengurusan ');
            document.form1.pengurusanNoSijil.focus();
        }
        else if ((jenisP == ""))
        {
            alert('Sila Pilih Jenis Pengenalan ');
            document.form1.pkodNegeri.focus();
        }
        else if ((pengurusanNoPengenalan == ""))
        {
            alert('Sila masukkan Nombor Pengenalan ');
            document.form1.pengurusanNoPengenalan.focus();
        }
        else if ((pengurusanAlamat1 == ""))
        {
            alert('Sila masukkan Alamat ');
            document.form1.pengurusanAlamat1.focus();
        }
        else if ((pengurusanPoskod == ""))
        {
            alert('Sila masukkan Poskod ');
            document.form1.pengurusanPoskod.focus();
        }
        else if ((pkodNegeri == ""))
        {
            alert('Sila Pilih Negeri ');
            document.form1.pkodNegeri.focus();
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
<s:form  name="form1" beanclass="etanah.view.strata.RMHSBadanPengurusanActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${edit}">
        <div class="subtitle">
            <p>
                <label>Id permohonan terdahulu :</label>
                ${idPermohonanTerdahulu}
            </p>&nbsp;
            <br><br>
            <br><br>
            <fieldset class="aras1">
                <legend>Kemasukan Maklumat Bangunan</legend>
                <p>
                    <label>Nama Perbadanan Pengurusan : </label><s:text name="pengurusanNama" id="pengurusanNama"/><em>*</em>
                </p>
                <p>
                    <%--<label>No Sijil Perbadanan Pengurusan : </label><s:text name="pengurusanNoSijil" id="pengurusanNoSijil"/><em>*</em>--%>
                </p>
                <p>
                    <label>Jenis Pengenalan :</label>
                    <s:select name="pengurusanJenisPengenalan" id="jenisP" value="${actionBean.pengurusanJenisPengenalan}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                    </s:select><em>*</em>
                </p>
    <%--            <p>
                    <label>Nombor Pengenalan : </label><s:text name="pengurusanNoPengenalan" id="pengurusanNoPengenalan" onkeyup="validateNumber(this,this.value);"/><em>*</em>
                </p>--%>
                <p>
                    <label>Alamat :</label>
                    <s:text name="pengurusanAlamat1" size="30" id="pengurusanAlamat1"/><em>*</em>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pengurusanAlamat2" size="30" id="pengurusanAlamat2"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pengurusanAlamat3" size="30" id="pengurusanAlamat3"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="pengurusanAlamat4" size="30" id="pengurusanAlamat4"/>
                <p>
                    <label>Poskod :</label>
                    <s:text name="pengurusanPoskod" maxlength="5" id="pengurusanPoskod" onkeyup="validateNumber(this,this.value);"/><em>*</em>
                </p>

                <p>
                    <label>Negeri :</label>
                    <s:select name="pengurusanKodNegeri" id="pkodNegeri" value="${actionBean.pengurusanKodNegeri}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select><em>*</em>
                </p>
              
                <p>
                    <br>
                    <label>&nbsp;</label>
                    <c:if test="${actionBean.mc eq null}">
                        <s:button name="simpanMaklumatBangunan" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                    </c:if>
                    <c:if test="${actionBean.mc ne null}">
                        <s:button name="updateMaklumatBangunan" id="simpan" value="Kemaskini" class="btn" onclick="save(this.name, this.form);"/>
                    </c:if>
                </p>
            </fieldset>
        </div>
    </c:if>

        <div class="subtitle">

            <p>
                <label>Id permohonan terdahulu :</label>
                ${idPermohonanTerdahulu}
            </p>
            
            <fieldset class="aras1">
                <legend>Maklumat Bangunan</legend>
                <p>
                    <label>Nama Perbadanan Pengurusan : </label>
                    ${actionBean.pengurusanNama}&nbsp;
                </p>
                <p>
                    <label>No Sijil Perbadanan Pengurusan : </label>
                    ${actionBean.pengurusanNoSijil}&nbsp;
                </p>
<%--                <p>
                    <label>Jenis Pengenalan :</label>
                    ${actionBean.pengurusanJenisPengenalan}&nbsp;
                </p>
                <p>
                    <label>Nombor Pengenalan : </label>
                    ${actionBean.pengurusanNoPengenalan}&nbsp;
                </p>--%>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.pengurusanAlamat1}&nbsp;
                </p>
                <c:if test="${actionBean.pengurusanAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pengurusanAlamat2}&nbsp;
                </p></c:if>
                <c:if test="${actionBean.pengurusanAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pengurusanAlamat3}&nbsp;
                </p></c:if>
                <c:if test="${actionBean.pengurusanAlamat4 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pengurusanAlamat4}&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>Poskod :</label>
                    ${actionBean.pengurusanPoskod}&nbsp;
                </p>

                <p>
                    <label>Negeri :</label>
                    ${actionBean.pengurusanKodNegeri}&nbsp;
                </p>
               
            </fieldset>
        </div>

</s:form>