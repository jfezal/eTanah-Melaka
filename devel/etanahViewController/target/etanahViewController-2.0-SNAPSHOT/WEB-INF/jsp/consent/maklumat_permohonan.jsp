<%--
    Document   : maklumat_Permohonan
    Created on : Oct 22, 2009, 5:13:38 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.1em;
        text-align:right;
        width:38.6em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:50em;
    }
</style>

<script type="text/javascript">

    function dateValidation(id, value) {
        var vsplit = value.split('/');
        var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today) {
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#' + id).val("");
        }
    }

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


</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.consent.MaklumatPermohonanActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>

            <table border="0">
                <tr> <td>&nbsp;</td></tr>
                <tr><td id="tdLabel">Permohonan :&nbsp;</td>
                    <td id="tdDisplay"><font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
                    </td>
                </tr>
                <tr><td id="tdLabel">ID Permohonan :&nbsp;</td>
                    <td id="tdDisplay">
                        ${actionBean.permohonan.idPermohonan}&nbsp;
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <c:if test="${actionBean.permohonan.permohonanSebelum ne null}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Sebelum</legend>
                <div class="content" align="center">

                    <c:choose>
                        <%--ONLY FOR URUSAN JKTL MELAKA--%>
                        <c:when test="${(fn:startsWith(actionBean.permohonan.idPermohonan, '04') && actionBean.permohonan.kodUrusan.kod eq 'PMJTL') &&
                                        (fn:startsWith(actionBean.permohonan.idPermohonan, '04') && actionBean.permohonan.kodUrusan.kod eq 'RMJTL')}">
                            <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPermohonanRujukanLuar}" cellpadding="0" cellspacing="0"
                                           requestURI="${pageContext.request.contextPath}/consent/maklumat_permohonan" id="lineRayuan">
                                <display:column title="Bil" sortable="true" style="width:40;">${lineRayuan_rowNum}</display:column>
                                <display:column property="permohonan.idPermohonan" title="ID Permohonan"/>
                                <display:column property="permohonan.keputusan.nama" title="Keputusan"  style="text-transform: uppercase;"/>
                                <display:column property="permohonan.ulasan" title="Ulasan" style="text-transform: uppercase;"/>
                                <display:column property="noSidang" title="Persidangan JKTL" style="text-transform: uppercase;"/>
                                <display:column title="Tarikh Keputusan">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${lineRayuan.permohonan.tarikhKeputusan}"/>
                                </display:column>
                                <display:column property="permohonan.keputusanOleh.jawatan" title="Keputusan Dimasuk Oleh" style="text-transform: uppercase;"/>
                            </display:table> 
                        </c:when>
                        <%--FOR URUSAN BIASA--%>
                        <c:otherwise>
                            <display:table style="width:90%;" class="tablecloth" name="${actionBean.senaraiPermohonanSebelum}" cellpadding="0" cellspacing="0"
                                           requestURI="${pageContext.request.contextPath}/consent/maklumat_permohonan" id="lineRayuan">
                                <display:column title="Bil" sortable="true" style="width:40;">${lineRayuan_rowNum}</display:column>
                                <display:column property="idPermohonan" title="ID Permohonan"/>
                                <display:column property="keputusan.nama" title="Keputusan"  style="text-transform: uppercase;"/>
                                <display:column property="ulasan" title="Ulasan" style="text-transform: uppercase;"/>
                                <display:column title="Tarikh Keputusan">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${lineRayuan.tarikhKeputusan}"/>
                                </display:column>
                                <display:column property="keputusanOleh.jawatan" title="Keputusan Dimasuk Oleh" style="text-transform: uppercase;"/>
                            </display:table>
                        </c:otherwise>
                    </c:choose>
                </div>
                <br/>
            </fieldset>
        </div>

    </c:if>
    <%---------------FOR URUSAN MMKN MELAKA------------------%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMK1'}">

        <c:if test="${edit}">
            <c:forEach items="${actionBean.permohonan.senaraiFasa}" var="senarai">       
                <c:if test="${senarai.idAliran eq 'Stage9'}">
                    <br/>
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Maklumat Rayuan Bayaran</legend>

                            <p>
                                <label> <font color="red">*</font>Jenis Pengurangan Bayaran :</label>
                                <s:radio name="permohonanUrusanRayuan.catatan" id="yuran" value="YURAN"/> Yuran Notis Kelulusan
                                <s:radio name="permohonanUrusanRayuan.catatan" id="sumbangan" value="SUMBANGAN"/> Sumbangan Tabung Amanah Rumah Kos Rendah                
                            </p>
                            <p>
                                <label>&nbsp;</label>
                                <s:button name="simpanPermohonanUrusanRayuan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </p>

                        </fieldset>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${!edit}">
            <c:if test="${actionBean.permohonanUrusanRayuan ne null}">
                <br/>
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Rayuan Bayaran</legend>
                        <p>
                            <label>Jenis Pengurangan Bayaran :</label>
                            <c:if test="${actionBean.permohonanUrusanRayuan.catatan eq 'YURAN'}">
                                YURAN NOTIS KELULUSAN
                            </c:if>
                            <c:if test="${actionBean.permohonanUrusanRayuan.catatan eq 'SUMBANGAN'}">
                                SUMBANGAN TABUNG AMANAH RUMAH KOS RENDAH
                            </c:if>
                        </p>
                    </fieldset>
                </div>
            </c:if>
        </c:if>
        <c:if test="${actionBean.stageId  ne 'Stage1' && actionBean.kodNegeri ne '05'}"> <%--insert by azwady.org 25/02/2014--%>
            <br/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Projek</legend>
                    <c:if test="${edit}">
                        <p>
                            <label> <font color="red">*</font>Jenis Projek :</label>
                                    <s:select name="idProjek" style="width:400px">
                                        <s:option value="">Sila Pilih</s:option>
                                <c:forEach items="${list.senaraiProjek}" var="item" varStatus="line">
                                    <s:option value="${item.idProjek}">
                                        ${item.namaPemaju} - ${item.noRujukanProjek} 
                                    </s:option>
                                </c:forEach>
                            </s:select>
                        </p>

                        <p>
                            <label>&nbsp;</label>
                            <s:button name="simpanPermohonan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                    <c:if test="${!edit}">
                        <p>
                            <label>Jenis Projek :</label>
                            <c:if test="${actionBean.permohonan.projek ne null}"><font style="text-transform:uppercase;">
                                    ${actionBean.permohonan.projek.namaPemaju} -  ${actionBean.permohonan.projek.noRujukanProjek} &nbsp; </font> </c:if>
                            <c:if test="${actionBean.permohonan.projek eq null}"> TIADA DATA </c:if>
                            </p>
                    </c:if>
                </fieldset>
            </div>
        </c:if>
    </c:if>

    <%---------------FOR URUSAN PMMAT CONSENT------------------%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' && edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                <legend>Maklumat Perintah</legend>
                <p>
                    <label><font color="red">*</font>Bil W.K.N.S :</label>
                            <s:text name="permohonanRujukanLuar.item" size="20"/>
                </p>
                <p>
                    <label> <font color="red">*</font>Jenis Perintah :</label>
                            <s:select name="permohonanRujukanLuar.catatan">
                                <s:option value="">Sila Pilih</s:option>
                        <s:option value="PPT">PERINTAH PENTADBIR TANAH</s:option>
                        <s:option value="PM">PERINTAH MAHKAMAH</s:option>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Perintah :</label>
                            <s:text name="tarikhPerintah" id="datepicker" class="datepicker" onchange="dateValidation(this.id, this.value)"/>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Perintah :</label>
                            <s:text name="permohonanRujukanLuar.noRujukan" size="20"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPermohonanRujukanLuar" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'DPWNA') && edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                <legend>Maklumat Perintah</legend>
                <p>
                    <label><font color="red">*</font>Bil W.K.N.S :</label>
                            <s:text name="permohonanRujukanLuar.item" size="20"/>
                </p>
                <p>
                    <label> <font color="red">*</font>Jenis Perintah :</label>
                            <s:select name="permohonanRujukanLuar.catatan" style="width:150px">
                                <s:option value="">Sila Pilih</s:option>
                        <s:option value="BE">BORANG E</s:option>
                        <s:option value="BF">BORANG F</s:option>
                        <s:option value="BT">BORANG T</s:option>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Nombor Pembahagian Guaman :</label>
                            <s:text name="permohonanRujukanLuar.noRujukan" size="40"/>
                </p>
                <p>
                    <label><font color="red">*</font>Tahun :</label>
                            <s:select name="permohonanRujukanLuar.nilai" style="width:150px">
                                <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiTahun}"/>
                    </s:select>
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Perintah :</label>
                            <s:text name="tarikhPerintah" id="datepicker" class="datepicker" onchange="dateValidation(this.id, this.value)"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPermohonanRujukanLuar" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Perintah</legend>
                <p>
                    <label>Bil W.K.N.S :</label>
                    ${actionBean.permohonanRujukanLuar.item}
                </p>
                <p>
                    <label>Jenis Perintah :</label>
                    <c:if test="${actionBean.permohonanRujukanLuar.catatan eq 'PPT'}">
                        PERINTAH PENTADBIR TANAH
                    </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.catatan eq 'PM'}">
                        PERINTAH MAHKAMAH
                    </c:if>
                </p>
                <p>
                    <label>Tarikh Perintah :</label>
                    ${actionBean.tarikhPerintah}
                </p>
                <p>
                    <label>Nombor Perintah :</label>
                    ${actionBean.permohonanRujukanLuar.noRujukan}
                </p>

            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'DPWNA' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Perintah</legend>
                <p>
                    <label>Bil W.K.N.S :</label>
                    ${actionBean.permohonanRujukanLuar.item}
                </p>
                <p>
                    <label>Jenis Perintah :</label>
                    <c:if test="${actionBean.permohonanRujukanLuar.catatan eq 'BE'}">
                        BORANG E
                    </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.catatan eq 'BF'}">
                        BORANG F
                    </c:if>
                    <c:if test="${actionBean.permohonanRujukanLuar.catatan eq 'BT'}">
                        BORANG T
                    </c:if>
                </p>
                <p>
                    <label>Nombor Pembahagian Guaman :</label>
                    ${actionBean.permohonanRujukanLuar.noRujukan}
                </p>
                <p>
                    <label>Tahun :</label>
                    ${actionBean.permohonanRujukanLuar.nilai}
                </p>
                <p>
                    <label>Tarikh Perintah :</label>
                    ${actionBean.tarikhPerintah}
                </p>


            </fieldset>
        </div>
    </c:if>

    <%---------------FOR URUSAN BANTAHAN TANAH ADAT------------------%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' && edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="permohonanUrusan.idUrusan"/>
                <legend>Maklumat Permohonan Terdahulu</legend>
                <p>
                    <label> <font color="red">*</font>Nama Permohonan :</label>
                            <s:select name="permohonanUrusan.catatan">
                                <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodUrusanBantahanTanahAdat}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPermohonanUrusan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Terdahulu</legend>
                <p>
                    <label>Nama Permohonan :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.kodUrusan.nama}</font>&nbsp;
                </p>
            </fieldset>
        </div>
    </c:if>
    <%---------------FOR NOMBOR RUJUKAN FAIL MANUAL------------------%>
    <c:if test="${fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, '/Consent/ADAT') ||
                  fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, '/Consent/pmwna') ||
                  actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
        <c:if test="${edit}">
            <br/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <s:hidden name="permohonanUrusanRuj.idUrusan"/>
                    <legend>Maklumat Nombor Rujukan Fail</legend>
                    <p>
                        <label>Nombor Rujukan Fail :</label>
                        <s:text name="permohonanUrusanRuj.catatan" size="40"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanPermohonanUrusanRujukan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </fieldset>
            </div>
        </c:if>
    </c:if>
    <c:if test="${fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, '/Consent/ADAT') ||
                  fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow, '/Consent/pmwna') ||
                  actionBean.permohonan.kodUrusan.kod eq 'BTADT'}">
        <c:if test="${!edit}">
            <br/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Nombor Rujukan Fail</legend>
                    <p>
                        <label>Nombor Rujukan Fail :</label>
                        <c:if test="${actionBean.permohonanUrusanRuj.catatan ne null}"><font style="text-transform:uppercase;">  ${actionBean.permohonanUrusanRuj.catatan}</font> </c:if>
                        <c:if test="${actionBean.permohonanUrusanRuj.catatan eq null}"> TIADA DATA </c:if>
                        </p>
                    </fieldset>
                </div>
        </c:if>
    </c:if>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <c:if test="${!editPenyerah}">
                <p>
                    <label>Nama :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNama}</font>&nbsp;
                </p>
                <c:if test="${actionBean.permohonan.kodPenyerah ne null}">
                    <p>
                        <label>Jenis Penyerah :</label>
                        <font style="text-transform:uppercase;">
                            <c:if test="${actionBean.permohonan.kodPenyerah.nama ne null}"> ${actionBean.permohonan.kodPenyerah.nama}&nbsp; </c:if>
                            </font>
                        </p>
                </c:if>
                <c:if test="${actionBean.permohonan.penyerahAlamat1 ne null}">
                    <p>
                        <label>Alamat :</label>
                        <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat1}</font>&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat2}</font> &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat3} </font> &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat4}</font>&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>Poskod :</label>
                    ${actionBean.permohonan.penyerahPoskod}&nbsp;
                </p>
                <p>
                    <label>Negeri :</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahNegeri.nama} </font>&nbsp;
                </p>
            </c:if>
            <c:if test="${editPenyerah}">
                <p>
                    <label>Nama :</label>
                    <s:text name="permohonan.penyerahNama" size="40"/>
                </p>
                <p>
                    <label>Jenis Penyerah :</label>
                    <s:select name="kodPenyerah" id="penyerah">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodPenyerah}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>Alamat :</label>
                    <s:text name="permohonan.penyerahAlamat1" size="40"/>
                </p>

                <p>
                    <label> &nbsp;</label>
                    <s:text name="permohonan.penyerahAlamat2" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="permohonan.penyerahAlamat3" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="permohonan.penyerahAlamat4" size="40"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="permohonan.penyerahPoskod" size="40" id="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label>Negeri :</label>
                    <s:select name="penyerahNegeri" id="negeri">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPenyerah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>
