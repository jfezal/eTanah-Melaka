<%-- 
    Document   : maklumat_permohonanL
    Created on : May 13, 2010, 4:26:37 PM
    Author     : mazurahayati.yusop
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function validate() {
        if ($('#penyerahnoruj').val() == "") {
            alert("Sila Masukkan No Ruj Penyerah");
            $('#penyerahnoruj').focus();
            return false;
        }
        return true;
    }

    $('#btgSimpan').hide();
    $('#bintangMerah').hide();
    function popupRujukan() {
        var r = confirm("Kemaskini No Rujukan Penyerah?");
        if (r == true) {
            $('#btgSimpan').show();
            $('#bintangMerah').show();
            $('#penyerahnoruj').focus();
        }
    }
</script>
<s:form  beanclass="etanah.view.stripes.lelong.MaklumatPermohonanLActionBean">
    <s:messages/>
    <s:errors/>&nbsp;

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label for="Permohonan">Permohonan : </label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan : </label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.permohonan.permohonanSebelum ne null}">
        <fieldset class="aras1">
            <legend>
                Permohonan Terdahulu
            </legend>

            <p>
                <label for="Permohonan">Permohonan : </label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.permohonanSebelum.kodUrusan.nama}</font>&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan : </label>
                ${actionBean.permohonan.permohonanSebelum.idPermohonan}&nbsp;
            </p>

        </fieldset>
    </c:if>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama : </label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNama}</font> &nbsp;
            </p>
            <%--<p>
                <label>Nombor Rujukan Penyerah :</label>
                <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.permohonan.penyerahNoRujukan ne null}"> ${actionBean.permohonan.penyerahNoRujukan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.penyerahNoRujukan eq null}"> Tiada Data </c:if>
                </font>
            </p>--%>

            <p>
                <label>Alamat : </label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat1}</font>&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat2}</font>&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat3}</font>&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat4}</font>&nbsp;
            </p>
            <p>
                <label>Poskod : </label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri : </label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNegeri.nama}</font>
            </p>
            <c:if test="${actionBean.permohonan.penyerahNoTelefon1 ne null}">
                <p>
                    <label>No Telefon : </label>
                    ${actionBean.permohonan.penyerahNoTelefon1}
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahEmail ne null}">
                <p>
                    <label>Email : </label>
                    ${actionBean.permohonan.penyerahEmail}
                </p>
            </c:if>
            <c:if test="${actionBean.displayBtn ne true}" >
                <p>
                    <label><font color="red">*</font> Nombor Rujukan Penyerah: </label>
                <s:text class="normal_text" id="penyerahnoruj" name="permohonan.penyerahNoRujukan"/>&nbsp;
                </p>

                <div class="content" align="right">
                    <p>
                    <s:button name="simpanPermohonan" id="" value="Simpan" class="btn" onclick="if (validate())
            doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </div>
            </c:if>

            <c:if test="${actionBean.displayBtn eq true}" >

                <p>
                    <label><font color="red" id="bintangMerah">*</font> Nombor Rujukan Penyerah : </label>
                <s:text class="normal_text" id="penyerahnoruj" name="permohonan.penyerahNoRujukan" >
                    ${actionBean.permohonan.penyerahNoRujukan}&nbsp;
                </s:text>                        

                <a id="" onclick="popupRujukan()" onmouseover="this.style.cursor = 'pointer';">
                    <img alt="Sila Klik Untuk Memperbaharui Nombor Rujukan" src='${pageContext.request.contextPath}/pub/images/edit.gif' />

                </a>

                </p><br>

                <div class="content" align="right">
                    <p>
                    <s:button name="simpanPermohonan" id="btgSimpan" value="Simpan" class="btn" onclick="if (validate())
            doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </div>  
            </c:if>

        </fieldset >
    </div>

    <c:if test="${fn:length(actionBean.peguamList) > 0}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Peguam Baru 
                </legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.peguamList}"
                                   pagesize="10" cellpadding="0" cellspacing="0" requestURI=""
                                   id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="idPermohonan.idPermohonan" title="ID Permohonan"/>
                        <display:column property="peguam.nama" title="Nama"/>
                        <display:column title="Alamat" class="alamat">
                            <font style="text-transform: uppercase">${line.peguam.alamat1}</font> <c:if test="${line.peguam.alamat2 ne null}">,</c:if>
                            <font style="text-transform: uppercase">${line.peguam.alamat2}</font> <c:if test="${line.peguam.alamat3 ne null}">,</c:if>
                            <font style="text-transform: uppercase">${line.peguam.alamat3}</font> <c:if test="${line.peguam.alamat4 ne null}">,</c:if>
                            <font style="text-transform: uppercase">${line.peguam.alamat4}</font> <c:if test="${line.peguam.poskod ne null}">,</c:if>
                            <font style="text-transform: uppercase">${line.peguam.poskod}</font> <c:if test="${line.peguam.negeri.kod ne null}">,</c:if>
                            <font style="text-transform: uppercase">${line.peguam.negeri.nama}</font>
                        </display:column>
                        <display:column property="peguam.noTelefon1" title="No. Telefon Bimbit"/>
                        <display:column property="peguam.noTelefon2" title="No. Telefon Pejabat"/>
						<display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk"format="{0,date,dd/MM/yyyy}"/>
						<display:column title="Status">
                                                <c:if test="${line.aktif eq 'Y'}">Aktif</c:if><c:if test="${line.aktif eq 'T'}">Tidak Aktif</c:if>
                                                    </display:column>
					</display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>
