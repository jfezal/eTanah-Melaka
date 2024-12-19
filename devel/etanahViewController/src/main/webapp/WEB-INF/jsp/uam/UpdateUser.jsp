<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return true;
        }
    }   
    
    function checkIdKaunter(f) {
        var caw = document.getElementById('pguna.kodCawangan.kod');
        $.get("${pageContext.request.contextPath}/uam/user_update?searchIdKaunter&kaunter=" +f+"&caw="+caw.value,
        function(data){
            //alert(data);
            if(data == '1'){
                alert('ID Kaunter telah wujud. Sila pilih kaunter lain');
                $("#idKaunter").val("");
            }    
        });
        return;
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

    function test(f) {
        $(f).clearForm();
    }

    function validateForm(f) {
        
        if(f.elements['pguna.nama'].value == '') {
            alert('Sila masukkan Nama.');
            return false;

        }  else if(f.elements['pguna.noPengenalan'].value == '') {
            alert('Sila masukkan No. Kad Pengenalan.');
            return false;

        }  else if(f.elements['pguna.kodCawangan.kod'].value == '') { 
             <c:if test="${actionBean.melaka}"> 
                alert('Sila pilih Jabatan yang berkaitan.');  
             </c:if>

             <c:if test="${!actionBean.melaka}"> 
                alert('Sila pilih Cawangan yang berkaitan.');
             </c:if>
                return false;

            } else if(f.elements['pguna.kodJabatan.kod'].value == '') {
             <c:if test="${actionBean.melaka}"> 
                alert('Sila pilih Unit yang berkaitan.');  
             </c:if>

             <c:if test="${!actionBean.melaka}"> 
                alert('Sila pilih Jabatan yang berkaitan.');
             </c:if>
                return false;
            }
            else if(f.elements['pguna.kodJawatan.kod'].value == '') {
                alert('Sila pilih Jawatan yang berkaitan.');
                return false;

            }else if(f.elements['pguna.email'].value == '') {
                alert('Sila masukkan Emel.');
                return false;

            }else if(f.elements['pguna.tahapSekuriti.kod'].value == '') {
                alert('Sila masukkan Kod Klasifikasi.');
                return false;
                
            }else if(f.elements['pguna.perananUtama.kod'].value == '') {
                alert('Sila pilih Peranan Utama yang berkaitan.');
                return false;
                
            } else if(!f.elements['pguna.status.kod'][0].checked && !f.elements['pguna.status.kod'][1].checked) {
                alert('Sila set status pengguna sama ada aktif atau tidak.');
                return false;
            
            } else if(!f.elements['pguna.penyeliaFlag'][0].checked && !f.elements['pguna.penyeliaFlag'][1].checked) {
                alert('Sila set status penyelia sama ada ya atau tidak.');
                return false;
            
            } else if(!f.elements['pguna.pelulusBayaranKurang'][0].checked && !f.elements['pguna.pelulusBayaranKurang'][1].checked) {
                alert('Sila set status pelulus bayaran kurang sama ada ya atau tidak.');
                return false;
            
            } 
        }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.UpdateUserActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <c:if test="${!papar}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Id Pengguna</legend>
                <font size="1" color="Red">Sila masukkan Id Pengguna untuk membuat carian.</font>
                <p>
                    <label><font color="red">*</font>Id Pengguna :</label>
                        <c:if test="${simpan}">
                            <s:text name="pguna.idPengguna" />
                            <s:submit name="searchUser" id="search" value="Cari" class="btn"/>
                        </c:if>
                        <c:if test="${!simpan}">
                            <s:text name="pguna.idPengguna" readonly="readonly"/>
                        </c:if>
						<font size="1" color="red">(cth: rahim.hamzah)</font>
                </p>
                
                <c:if test="${kemaskini}">
                    <p>
                        <label>&nbsp;</label>
                        <a href="user_password?idPengguna=${actionBean.pguna.idPengguna}">Tukar Kata Laluan</a>
                    </p>
                </c:if>
            </fieldset >
            <c:if test="${kemaskini}">
            <fieldset class="aras1">
                <legend>Maklumat Asas Pengguna</legend>
                <label><font color="red">*</font> Last Update Password By :</label> ${actionBean.pguna.dimasukKlaluan}
                <br>
                <p>
                    <label><font color="red">*</font>Nama :</label>
                        <s:text name="pguna.nama" id="pguna.nama" style="width:250px" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>No. Kad Pengenalan :</label>
                    <s:text name="pguna.noPengenalan" id="pguna.noPengenalan"  onkeyup="validateNumber(this,this.value);" maxlength="12" style="width:250px" onblur="this.value=this.value.toUpperCase();"/>
						<font size="1" color="red">(cth: 840706045494)</font>
                </p>
                <p>
                    <c:if test="${actionBean.melaka}"> 
                        <label><font color="red">*</font>Jabatan :</label>
                        </c:if>

                    <c:if test="${!actionBean.melaka}"> 
                        <label><font color="red">*</font>Cawangan :</label>
                        </c:if>

                    <s:select name="pguna.kodCawangan.kod" id="pguna.kodCawangan.kod" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodCawangan}" value="kod" label="name"/>
                    </s:select>
                </p>
                <p>
                    <c:if test="${!actionBean.melaka}">
                        <label><font color="red">*</font>Jabatan :</label>
                        </c:if>
                        <c:if test="${actionBean.melaka}">
                        <label><font color="red">*</font>Unit :</label>
                        </c:if>
                        <s:select name="pguna.kodJabatan.kod" id="pguna.kodJabatan.kod" style="width:250px" onchange="doSomething2(this.value,this.form);">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJabatan}" value="kod" label="nama" id="jabatan"/>
                    </s:select>
                </p>
                <%--<p>
                    <label><font color="red">*</font>Jawatan :</label>
                        <s:text name="pguna.jawatan" style="width:250px" onblur="this.value=this.value.toUpperCase();"/>
                </p>--%>
                <p>
                    <label><font color="red">*</font>Jawatan:</label>
                        <s:select name="pguna.kodJawatan.kod" id="pguna.kodJawatan.kod" style="width:397px">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodJawatan}" value="kod" label="nama" id="jawatan"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Emel :</label>
                        <s:text name="pguna.email" id="pguna.email" style="width:250px"/>
                </p>
                <br />
                <p>
                    <label><font color="red">*</font>Kod Klasifikasi :</label>
                        <s:select name="pguna.tahapSekuriti.kod" id="pguna.tahapSekuriti.kod" style="width:250px">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodKlasifikasi}" value="kod" label="nama"/>
                    </s:select>                    
                </p>
                <p>
                    <label><font color="red">*</font>Peranan Utama :</label>
                        <s:select name="pguna.perananUtama.kod" id="pguna.perananUtama.kod" style="width:370px">
                            <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodPeranan}" value="kod" label="nama"/> 
                    </s:select>                   
                </p>
                <%--p>
                    <label><font color="red">*</font>Imej Tandatangan :</label>
                    <s:image name=""/>
                </p--%>
                <p>
                    <label>Id Kaunter :</label>
                    <s:text name="pguna.idKaunter" id="idKaunter" maxlength="2" style="width:80px" onkeyup="validateNumber(this,this.value);" onblur="checkIdKaunter(this.value);"/>
                    <font size="1" color="red">* Kegunaan Unit Hasil</font>
                </p>
                <p>
                    <label>Kad Kuasa :</label>
                    <s:text name="pguna.kadKuasa" id="kadKuasa" maxlength="20" style="width:80px"/>
                    <font size="1" color="red">* Kegunaan Unit Hasil</font>
                </p>
                <p>
                    <label><font color="red">*</font>Aktif :</label>

                    <c:set var="yes" value=""/>
                    <c:set var="no" value=""/>                
                    <c:if test="${actionBean.pguna.status.kod eq 'A'}">
                        <c:set var="yes" value="checked"/>
                    </c:if>
                    <c:if test="${actionBean.pguna.status.kod eq 'X'}">
                        <c:set var="no" value="checked"/>
                    </c:if>

                    <input type="radio" name="pguna.status.kod" id="pguna.status.kod" value='A' ${yes}/>Ya
                    <input type="radio" name="pguna.status.kod" id="pguna.status.kod" value='X' ${no}/>Tidak
                </p>
                <p>
                    <label><font color="red">*</font>Penyelia :</label>

                    <c:set var="yes" value=""/>
                    <c:set var="no" value=""/>                
                    <c:if test="${actionBean.pguna.penyeliaFlag eq 'Y'}">
                        <c:set var="yes" value="checked"/>
                    </c:if>
                    <c:if test="${actionBean.pguna.penyeliaFlag eq 'T'}">
                        <c:set var="no" value="checked"/>
                    </c:if>

                    <input type="radio" name="pguna.penyeliaFlag" id="pguna.penyeliaFlag" value='Y' ${yes}/>Ya
                    <input type="radio" name="pguna.penyeliaFlag" id="pguna.penyeliaFlag" value='T' ${no}/>Tidak
                </p>
                <p>
                    <label><font color="red">*</font>Pelulus Bayar Kurang :</label>

                    <c:set var="yes" value=""/>
                    <c:set var="no" value=""/>                
                    <c:if test="${actionBean.pguna.pelulusBayaranKurang eq 'Y'}">
                        <c:set var="yes" value="checked"/>
                    </c:if>
                    <c:if test="${actionBean.pguna.pelulusBayaranKurang eq 'T'}">
                        <c:set var="no" value="checked"/>
                    </c:if>

                    <input type="radio" name="pguna.pelulusBayaranKurang" id="pguna.pelulusBayaranKurang" value='Y' ${yes}/>Ya
                    <input type="radio" name="pguna.pelulusBayaranKurang" id="pguna.pelulusBayaranKurang" value='T' ${no}/>Tidak
                </p>
            </fieldset>
            <br /></c:if>
            <p>
                <label>&nbsp;</label>

                <c:if test="${kemaskini}">
                    <s:submit name="updateUser" id="save" value="Kemaskini" class="btn" onclick="return validateForm(this.form);"/>
                </c:if>
                <s:submit name="showForm" value="Isi Semula" class="btn" onclick="test(this.form);"/>
                <%--<s:submit name="kembali1" value="Kembali" class="btn"/>--%>
            </p>
        </div>
    </c:if>
    <c:if test="${papar}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Id Pengguna Untuk papar</legend>
                <font size="1" color="Red">Sila masukkan Id Pengguna untuk membuat carian.</font>
                <p>
                    <label><font color="red">*</font>Id Pengguna :</label>
                        ${actionBean.pguna.idPengguna}

                </p>
                <c:if test="${kataLaluan}">
                    <p>
                        <label><font color="red">*</font>Kata Laluan :</label>
                            <s:password value="${actionBean.pguna.password}" name="pguna.password" maxlength="10"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Pengesahan Kata Laluan :</label>
                            <s:password value="${actionBean.pKataLaluan}" name="pKataLaluan" maxlength="10"/>
                    </p>
                </c:if>
                <c:if test="${kemaskini}">
                    <p>
                        <label>&nbsp;</label>
                        <a href="user_password?idPengguna=${actionBean.pguna.idPengguna}">Tukar Kata Laluan</a>
                    </p>
                </c:if>
            </fieldset >
            <fieldset class="aras1">
                <legend>Maklumat Asas Pengguna</legend>
                <p>
                    <label><font color="red">*</font>Nama :</label>
                        ${actionBean.pguna.nama}
                </p>
                <p>
                    <label><font color="red">*</font>No. Kad Pengenalan :</label>
                        ${actionBean.pguna.noPengenalan}
                </p>

                <p>
                    <c:if test="${actionBean.melaka}"> <label><font color="red">*</font>Jabatan :</label></c:if>
                    <c:if test="${!actionBean.melaka}"> <label><font color="red">*</font>Cawangan :</label></c:if>
                        ${actionBean.namaCaw}
                </p>

                <p>
                    <label><font color="red">*</font>Jawatan :</label>
                        ${actionBean.jawatan}                       
                </p>

                <p>
                    <label><font color="red">*</font>Emel :</label>
                        ${actionBean.pguna.email}
                </p>
                <c:if test="${actionBean.namaKKlasifikasi ne ''}">
                    <p>
                        <label><font color="red">*</font>Kod Klasifikasi :</label>
                            ${actionBean.namaKKlasifikasi}
                    </p>
                </c:if>
                <p>
                    <label><font color="red">*</font>Peranan Utama :</label>
                        ${actionBean.namaPeranan}
                </p>

                <p>
                    <label>Id Kaunter :</label>
                    <c:if test = "${actionBean.pguna.idKaunter ne null}">
                        ${actionBean.pguna.idKaunter}
                    </c:if>
                    <c:if test = "${actionBean.pguna.idKaunter eq null}">
                        -
                    </c:if>
                </p>
                <p>
                    <label>Kad Kuasa :</label>
                    <c:if test = "${actionBean.pguna.kadKuasa ne null}">
                        ${actionBean.pguna.kadKuasa}
                    </c:if>
                    <c:if test = "${actionBean.pguna.kadKuasa eq null}">
                        -
                    </c:if>
                </p>
                <p>
                    <label><font color="red">*</font>Aktif :</label>

                    <c:set var="yes" value=""/>
                    <c:set var="no" value=""/>                
                    <c:if test="${actionBean.pguna.status.kod eq 'A'}">
                        Ya
                    </c:if>
                    <c:if test="${actionBean.pguna.status.kod eq 'X'}">
                        Tidak
                    </c:if>
                </p>
                <p>
                    <label><font color="red">*</font>Penyelia :</label>

                    <c:set var="yes" value=""/>
                    <c:set var="no" value=""/>                
                    <c:if test="${actionBean.pguna.penyeliaFlag eq 'Y'}">
                        Ya
                    </c:if>
                    <c:if test="${actionBean.pguna.penyeliaFlag eq 'T'}">
                        Tidak
                    </c:if>
                </p>
                <p>
                    <label><font color="red">*</font>Pelulus Bayar Kurang :</label>

                    <c:set var="yes" value=""/>
                    <c:set var="no" value=""/>                
                    <c:if test="${actionBean.pguna.pelulusBayaranKurang eq 'Y'}">
                        Ya
                    </c:if>
                    <c:if test="${actionBean.pguna.pelulusBayaranKurang eq 'T'}">
                        Tidak
                    </c:if>
                </p>
            </fieldset>
            <br />
            <p>
                <label>&nbsp;</label>

                <c:if test="${simpan}">
                    <s:submit name="kembali" id="save" value="Kembali" class="btn"/>
                </c:if>
            </p>
        </div>
    </c:if>
</s:form>
