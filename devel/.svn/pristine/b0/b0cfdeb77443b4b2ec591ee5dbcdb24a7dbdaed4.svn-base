<%--
    Document   : maklumat_bangunan
    Created on : Jul 22, 2010, 2:45:10 PM
    Author     : User
    Modified By: Murali Aug 23, 2011
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <title>Strata</title>
    </head>
    <body>
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
                var pengurusanNama = document.getElementById("pengurusanNama").value;
                var pengurusanAlamat1 = document.getElementById("pengurusanAlamat1").value;
                var pengurusanPoskod = document.getElementById("pengurusanPoskod").value;
                var pkodNegeri = document.getElementById("pkodNegeri").value;
                var pengurusanNoRujukan = document.getElementById("pengurusanNoRujukan").value;
                if(pengurusanNama ==""){
                    alert('Sila masukkan Nama Perbadanan Pengurusan ');
                    document.formstr.pengurusanNama.focus();
            <%--document.formstr.pkodNegeri.focus();--%>
                        return false;

                    } else if ((pengurusanAlamat1 == ""))
                    {
                        alert('Sila masukkan alamat');
                        document.formstr.pengurusanAlamat1.focus();
            <%--document.formstr.pkodNegeri.focus();--%>
                        return false;
                    }else if ((pengurusanPoskod == ""))
                    {
                        alert('Sila masukkan Poskod ');
                        document.formstr.pengurusanPoskod.focus();
            <%--document.formstr.pkodNegeri.focus();--%>
                    }
                    else if ((pkodNegeri == ""))
                    {
                        alert('Sila Pilih Negeri ');
            <%-- document.form1.pkodNegeri.focus();--%>
                        document.formstr.pkodNegeri.focus();
                    }
                    else if ((pengurusanNoRujukan == ""))
                    {
                        alert('Sila masukkan Nombor Rujukan PTG ');
                        document.formstr.pengurusanNoRujukan.focus();
                    }else{
                        $.blockUI({
                            message: $('#displayBox'),
                            css: {
                                top:  ($(window).height() - 50) /2 + 'px',
                                left: ($(window).width() - 50) /2 + 'px',
                                width: '50px'
                            }
                        });
                        var q = $(f).formSerialize();
                        var url = f.action + '?' + event;
                        $.post(url,q,
                        function(data){
                            $('#page_div').html(data);
                            $.unblockUI();
                        },'html');
                        return true;
                    }
                }

                function clearForm(){               
                    var url = "${pageContext.request.contextPath}/strata/badanPengurusan?resetForm";                   
                    $.post(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }
                    
            
        </script>
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <%--Original <s:form  name="form1" beanclass="etanah.view.strata.BadanPengurusanActionBean">--%>
        <s:form  name="formstr" beanclass="etanah.view.strata.BadanPengurusanActionBean">
            <s:messages/>
            <s:errors/>

            <c:if test="${edit}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Perbadanan Pengurusan </legend>
                        <p>
                            Yang bertanda(<em>*</em>) adalah wajib diisi.
                        </p>
                        <p>
                            <label><em>*</em>Nama : </label><s:text size="85" name="pengurusanNama" id="pengurusanNama"/>
                        </p>
                        <p>
                            <label><em>*</em>Alamat :</label>
                            <s:text name="pengurusanAlamat1" size="45" id="pengurusanAlamat1"/>
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pengurusanAlamat2" size="45" id="pengurusanAlamat2" />
                        </p>
                        <p>
                            <label> &nbsp;</label>
                            <s:text name="pengurusanAlamat3" size="45" id="pengurusanAlamat3" />
                        </p>
                        <p>
                            <label> Bandar :</label>
                            <s:text name="pengurusanAlamat4" size="45" id="pengurusanAlamat4" />
                        <p>
                            <label><em>*</em>Poskod :</label>
                            <s:text name="pengurusanPoskod" size="45" maxlength="5" id="pengurusanPoskod" onkeyup="validateNumber(this,this.value);"/>
                        </p>

                        <p>
                            <label><em>*</em>Negeri :</label> 
                            <s:select name="pengurusanKodNegeri"  style="width:286px;" id="pkodNegeri">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                            <p>
                                <label>Nombor Telefon 1 :</label>
                                <s:text name="pengurusanTelefon" size="45" maxlength="10" id="pengurusanTelefon" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label>Nombor Telefon 2 :</label>
                                <s:text name="pengurusanTelefon2" size="45" maxlength="10" id="pengurusanTelefon2" onkeyup="validateNumber(this,this.value);"/>
                            </p>
                            <p>
                                <label><em>*</em>Nombor Rujukan PTG:</label>
                                <s:text name="pengurusanNoRujukan" size="45" maxlength="50" id="pengurusanNoRujukan" />
                            </p>
                        <p>
                            <br>
                            <label>&nbsp;</label>
                            <c:if test="${actionBean.mc eq null}">
                                <s:button name="simpanMaklumatBadan" id="simpan" value="Simpan" class="btn" onclick="return save(this.name, this.form);"/>

                            </c:if>
                            <c:if test="${actionBean.mc ne null}">
                                <s:button name="updateMaklumatBadan" id="simpan" value="Kemaskini" class="btn" onclick="save(this.name, this.form);"/>
                            </c:if>
                            <s:button class="btn" value="Isi Semula" name="resetForm" onclick="clearForm()"/>
                        </p>                       
                    </fieldset>
                </div>
            </c:if>

            <c:if test="${!edit}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Perbadanan Pengurusan</legend>

                        <p>
                            <label>Nama : </label>
                            ${actionBean.pengurusanNama}&nbsp;
                        </p>

                        <p>
                            <label>Alamat :</label>
                            ${actionBean.pengurusanAlamat1}&nbsp;
                        </p>
                        <c:if test="${actionBean.pengurusanAlamat2 ne null}">
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.pengurusanAlamat2}&nbsp;
                            </p>
                        </c:if>
                        <c:if test="${actionBean.pengurusanAlamat3 ne null}">
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.pengurusanAlamat3}&nbsp;
                            </p>
                        </c:if>
                        <%--<c:if test="${actionBean.pengurusanAlamat4 ne null}">--%>
                        <p>
                            <label>Bandar :</label>
                            ${actionBean.pengurusanAlamat4}&nbsp;
                        </p>
                        <%--</c:if>--%>
                        <p>
                            <label>Poskod :</label>
                            ${actionBean.pengurusanPoskod}&nbsp;
                        </p>

                        <p>
                            <label>Negeri :</label>
                            <font style="text-transform: uppercase">${actionBean.pengurusanNamaNegeri}&nbsp;</font>
                        </p>
                        <c:if test="${actionBean.pengurusanTelefon ne null}">
                        <p>
                            <label>Nombor Telefon 1 :</label>
                            ${actionBean.pengurusanTelefon}&nbsp;
                        </p>
                        </c:if>
                        <c:if test="${actionBean.pengurusanTelefon2 ne null}">
                        <p>
                            <label>Nombor Telefon 2 :</label>
                            ${actionBean.pengurusanTelefon2}&nbsp;
                        </p>
                        </c:if>

                    </fieldset>
                </div>
            </c:if>
        </s:form>
    </body>
</html>