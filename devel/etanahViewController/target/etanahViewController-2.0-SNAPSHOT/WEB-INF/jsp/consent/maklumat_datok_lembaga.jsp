<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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

    function getDatokLembaga(kodSuku){

        var luak = $('#namaLuak').val();
        if(kodSuku != ""){
            var url = '${pageContext.request.contextPath}/consent/maklumat_datok_lembaga?getDatokLembaga&kodSuku='+kodSuku+'&luak='+luak;
            $.get(url,
            function(data){
                $('#display').html(data);
            });
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.MaklumatDatokLembagaActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="display">
        <s:hidden name="senaraiRujukan.kod"/>
        <s:hidden name="permohonanUrusanLuak.idUrusan"/>
        <s:hidden name="permohonanUrusanLembaga.idUrusan"/>

        <fieldset class="aras1">
            <legend>Maklumat Datok Lembaga</legend>
            <c:if test="${edit}">
                <p id="luak">
                    <label for="luak"><font color="red">*</font>Luak :</label>
                    <s:select name="permohonanUrusanLuak.catatan" style="width:280px" id="namaLuak">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodLuak}" label="nama" value="nama"/>
                    </s:select>
                </p>
                <p id="suku">
                    <label for="Suku"><font color="red">*</font>Jenis Suku :</label>
                    <s:select name="senaraiRujukan.senarai.kod" style="width:280px" onchange="getDatokLembaga(this.value);">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.kodSenaraiList}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Nama Gelaran :</label>
                    <s:text name="senaraiRujukan.perihal"  size="42" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nama Datok Lembaga :</label>
                    <s:text name="senaraiRujukan.nama"  size="42" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="alamat"><font color="red">*</font>Alamat :</label>
                    <s:text name="senaraiRujukan.alamat.alamat1" id="suratAlamat1" size="42" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="senaraiRujukan.alamat.alamat2" id="suratAlamat2" size="42" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="senaraiRujukan.alamat.alamat3" id="suratAlamat3" size="42" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> Bandar :</label>
                    <s:text name="senaraiRujukan.alamat.alamat4" id="suratAlamat4" size="42" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label><font color="red">*</font>Poskod :</label>
                    <s:text name="senaraiRujukan.alamat.poskod" id="suratPoskod" size="42" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>

                <p>
                    <label for="Negeri"><font color="red">*</font>Negeri :</label>
                    <s:select name="negeri" id="suratNegeri" style="width:280px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>Nombor Telefon :</label>
                    <s:text name="senaraiRujukan.noTel1" id="noTelefon" size="42" maxlength="15" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
            <c:if test="${!edit}">
                <p>
                    <label>Luak :</label>
                    <c:if test="${actionBean.permohonanUrusanLuak.catatan ne null}"><font style="text-transform:uppercase;"> ${actionBean.permohonanUrusanLuak.catatan}&nbsp; </font></c:if>
                    <c:if test="${actionBean.permohonanUrusanLuak.catatan eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Jenis Suku :</label>
                    <c:if test="${actionBean.senaraiRujukan.senarai ne null}"><font style="text-transform:uppercase;">  ${actionBean.senaraiRujukan.senarai.nama}&nbsp; </font></c:if>
                    <c:if test="${actionBean.senaraiRujukan.senarai eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Nama Gelaran :</label>
                    <c:if test="${actionBean.senaraiRujukan.perihal ne null}"><font style="text-transform:uppercase;">  ${actionBean.senaraiRujukan.perihal}</font> </c:if>
                    <c:if test="${actionBean.senaraiRujukan.perihal eq null}"> TIADA DATA </c:if>
                    </p>

                    <p>
                        <label>Nama Datok Lembaga :</label>
                    <c:if test="${actionBean.senaraiRujukan.nama ne null}"><font style="text-transform:uppercase;">  ${actionBean.senaraiRujukan.nama}</font> </c:if>
                    <c:if test="${actionBean.senaraiRujukan.nama eq null}"> TIADA DATA </c:if>
                    </p>


                <c:if test="${actionBean.senaraiRujukan.alamat.alamat1 ne null}">
                    <p>
                        <label>Alamat :</label>
                        <font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.alamat.alamat1}</font>&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.senaraiRujukan.alamat.alamat2 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        <font style="text-transform:uppercase;">${actionBean.senaraiRujukan.alamat.alamat2}</font> &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.senaraiRujukan.alamat.alamat3 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        <font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.alamat.alamat3} </font> &nbsp;
                    </p>
                </c:if>
                <p>
                    <label> Bandar :</label>
                    <c:if test="${actionBean.senaraiRujukan.alamat.alamat4 ne null}"> <font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.alamat.alamat4}</font> </c:if>
                    <c:if test="${actionBean.senaraiRujukan.alamat.alamat4 eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Poskod :</label>
                    <c:if test="${actionBean.senaraiRujukan.alamat.poskod ne null}"> ${actionBean.senaraiRujukan.alamat.poskod}&nbsp; </c:if>
                    <c:if test="${actionBean.senaraiRujukan.alamat.poskod eq null}"> TIADA DATA </c:if>

                    </p>

                    <p>
                        <label for="Negeri">Negeri :</label>
                    <c:if test="${actionBean.senaraiRujukan.alamat.negeri.nama ne null}"><font style="text-transform:uppercase;"> ${actionBean.senaraiRujukan.alamat.negeri.nama}</font> </c:if>
                    <c:if test="${actionBean.senaraiRujukan.alamat.negeri.nama eq null}"> TIADA DATA </c:if>

                    </p>
                    <p>
                        <label>Nombor Telefon :</label>
                    <c:if test="${actionBean.senaraiRujukan.noTel1 ne null}"> ${actionBean.senaraiRujukan.noTel1}&nbsp; </c:if>
                    <c:if test="${actionBean.senaraiRujukan.noTel1 eq null}"> TIADA DATA </c:if>
                    </p>
            </c:if>
        </fieldset>
    </div>
</s:form>

