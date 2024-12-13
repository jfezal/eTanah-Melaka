<%-- 
    Document   : maklumat_projek
    Created on : Sep 2, 2010, 2:21:27 PM
    Author     : faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function () {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function validateNumber(elmnt, content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric(strString)
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++)
        {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1)
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function save(event, f) {
        var namaProjek = document.getElementById("namaProjek").value;
        ;
        if ((namaProjek == ""))
        {
            alert('Sila masukkan Nama Projek');
            document.formstr.namaProjek.focus();
            return false;
        }
        if ((namaProjek != "")) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url, q,
                    function (data) {
                        $('#page_div').html(data);
                        $.unblockUI();
                    }, 'html');
            return true;
        }
    }

    function nonNumber(elmnt, inputTxt) {
        var a = document.getElementById('pengukur_noic');

        if (isNaN(a.value)) {
            alert("Nombor tidak sah.Sila masukkan Semula");
            $("#pengukur_noic").focus();
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.MaklumatProjekActionBean" name="formstr">
    <s:messages/>
    <s:errors/>

    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Projek</legend>
                <s:hidden name="idsblm" id="idsblm"/>

                <p>Yang bertanda(<em>*</em>) adalah wajib diisi.</p>

                <p><label>Nama Skim/Projek :</label>
                    <s:text name="mohonStrata.nama" size="50" id="namaProjek" class="normal_text"/><em>*</em></p>
                <p>

                    <label>Jenis Kos Rendah :</label><s:radio name="kosRendah" id="kosRendah" value="Y"></s:radio> Ya
                    <s:radio name="kosRendah" value="N"></s:radio> Tidak
                    </p>

                <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'PBBS' || actionBean.permohonan.kodUrusan.kod eq 'PBBD'
                                || actionBean.permohonan.kodUrusan.kod eq 'PSBS' || actionBean.permohonan.kodUrusan.kod eq 'PBS'}">
                      <p><label>No Sijil SiFUS :</label>
                          <s:text name="mohonStrata.noBuku" size="50" id="noBuku" class="normal_text" />
                      </p>
                      <p><label>Tarikh SiFUS diluluskan :</label>
                          <s:text name="mohonStrata.tarikhSiap" size="50" id="tarikhSiap" class="datepicker" formatPattern="dd/MM/yyyy"/>
                      </p>
                      <p><label>No Sijil CF :</label>
                          <s:text name="mohonStrata.cfNoSijil" size="50" id="cfNoSijil" class="normal_text"/>
                      </p>
                      <p><label>Tarikh Sijil CF :</label>
                          <s:text name="mohonStrata.cfTarikhKeluar" size="50" id="cfTarikhKeluar" class="datepicker" formatPattern="dd/MM/yyyy"/>
                      </p>
                      <p><label>No Siri CPSP :</label>
                          <s:text name="mohonStrata.mcNoSijil" size="50" id="mcNoSijil" class="normal_text"/>
                      </p>
                      <p><label>Tarikh CPSP diluluskan :</label>
                          <s:text name="mohonStrata.mcTrhSijil" size="50" id="mcTrhSijil" class="datepicker" formatPattern="dd/MM/yyyy"/>
                      </p>
                      <p><label>No Rujukan JUPEM :</label>
                          <s:text name="mohonStrata.mcNoRuj" size="50" id="mcNoRuj" class="normal_text"/>
                      </p>
                      <p><label>Tarikh Rujukan JUPEM :</label>
                          <s:text name="mohonStrata.mcTrhRuj" size="50" id="mcTrhRuj" class="datepicker" formatPattern="dd/MM/yyyy"/>
                      </p>
                </c:if>

                <c:if test="${manual}">
                    <p><label>No Skim :</label>&nbsp;
                        <s:text name="noSkim" size="20" maxlength = "10" id="noSkim" class="normal_text"/>
                    </p>
                    <p>
                        <label>Kegunaan Bangunan : </label>&nbsp;
                        <s:select name="kodKegunaanBangunan" value="${actionBean.kodKegunaanBangunan}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanBangunan}" label="nama" value="kod"/>
                        </s:select>
                    </p>
                    <p>
                        <label>Kegunaan Petak :</label>&nbsp;
                        <s:select name="kodKegunaanPetak" id="kodKegunaanPetak">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKegunaanPetak}" label="nama" value="kod"/>
                        </s:select>
                    </p>

                </c:if>
                <c:if test="${actionBean.mhnSkim ne null}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan" value="Kemaskini" class="btn" onclick=" return save(this.name, this.form);"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.mhnSkim eq null}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>

            </fieldset>
        </div>
    </c:if>

    <c:if test="${disable}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Projek</legend>
                <p>
                    <label>Nama Projek :</label>
                    ${actionBean.mohonStrata.nama}&nbsp;
                </p>

                <p>
                    <label>Jenis Kos Rendah :</label>
                    <c:if test="${actionBean.mohonStrata.kosRendah eq 'Y'}">
                        Ya
                    </c:if>
                    <c:if test="${actionBean.mohonStrata.kosRendah eq 'N'}">
                        Tidak
                    </c:if>
                </p>
                <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'PBBS' || actionBean.permohonan.kodUrusan.kod eq 'PBBD'
                                || actionBean.permohonan.kodUrusan.kod eq 'PSBS' || actionBean.permohonan.kodUrusan.kod eq 'PBS'}">
                      <p><label>No Sijil SiFUS :</label>
                          <s:text name="mohonStrata.noBuku" size="50" id="noBuku" class="normal_text" readonly="true"/>
                      </p>
                      <p><label>Tarikh SiFUS diluluskan :</label>
                          <s:text name="mohonStrata.tarikhSiap" size="50" id="tarikhSiap" class="datepicker" readonly="true"formatPattern="dd/MM/yyyy"/>
                      </p>
                      <p><label>No Sijil CF :</label>
                          <s:text name="mohonStrata.cfNoSijil" size="50" id="cfNoSijil" readonly="true" class="normal_text"/>
                      </p>
                      <p><label>Tarikh Sijil CF :</label>
                          <s:text name="mohonStrata.cfTarikhKeluar" size="50" id="cfTarikhKeluar" readonly="true" class="datepicker" formatPattern="dd/MM/yyyy"/>
                      </p>
                      <p><label>No Siri CPSP :</label>
                          <s:text name="mohonStrata.mcNoSijil" size="50" id="mcNoSijil" readonly="true" class="normal_text"/>
                      </p>
                      <p><label>Tarikh CPSP diluluskan :</label>
                          <s:text name="mohonStrata.mcTrhSijil" size="50" id="mcTrhSijil" class="datepicker" readonly="true" formatPattern="dd/MM/yyyy"/>
                      </p>
                      <p><label>No Rujukan JUPEM :</label>
                          <s:text name="mohonStrata.mcNoRuj" size="50" id="mcNoRuj" class="normal_text" readonly="true"/>
                      </p>
                      <p><label>Tarikh Rujukan JUPEM :</label>
                          <s:text name="mohonStrata.mcTrhRuj" size="50" id="mcTrhRuj" class="datepicker" readonly="true" formatPattern="dd/MM/yyyy"/>
                      </p>
                </c:if>

            </fieldset>
        </div>
    </c:if>
</s:form>