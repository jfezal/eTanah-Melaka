<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    function popup(kod)
    {
        window.open("${pageContext.request.contextPath}/strata/emel?popupKogAgensi&kod=" + kod, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,scrollbars=1,width=910,height=600");
    }

    function hapus(x) {
        if (confirm('Adakah anda pasti untuk hapus kod Agensi ini')) {
            var url = '${pageContext.request.contextPath}/strata/emel?delAgensi&kodAgensiSTR=' + x;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
</script>
<s:form beanclass="etanah.view.strata.MaklumatEmel">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
        <c:if test="${!add}">
            <table>
                <!--agensi-->
                <display:table style="width:80%" class="tablecloth" name="${actionBean.agensi}"  id="line">
                    <display:column title="Kod Agensi" property="kod" />
                    <display:column title="Nama Agensi" property="nama" />
                    <display:column title="Alamat Emel" property="emel" />
                    <display:column title="Status">
                        <c:if test = "${line.aktif eq 'T'}">
                            Tidak Aktif
                        </c:if>
                        <c:if test = "${line.aktif eq 'Y'}">
                            Aktif
                        </c:if>
                    </display:column>
                    <display:column title="Tindakan">
                        <div align="center">
                            <p align="center">
                                <img alt='Klik Untuk Papar' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="popup('${line.kod}');" onmouseover="this.style.cursor = 'pointer';">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif'
                                     onclick="hapus('${line.kod}');" onmouseover="this.style.cursor = 'pointer';">
                            </p>
                        </div>
                    </display:column>

                </display:table>
                <s:button name="addAgensi" value="Tambah Emel" class="btn" onclick="doSubmit(this.form,this.name,'page_div')" />
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SFUS' || actionBean.permohonan.kodUrusan.kod eq 'PFUS'}">
                    <s:button name="hantarEmel" value="Hantar Emel" class="btn" onclick="doSubmit(this.form,this.name,'page_div')" />
                </c:if>
            </table>
        </c:if>
        <c:if test="${add}">

            <legend>Maklumat Agensi</legend>
            <p>
                <label>Kod Agensi :</label><s:text name="kodAgensi.kod" onkeyup="this.value=this.value.toUpperCase();" size="10" maxlength="10"/>
            </p>
            <p>
                <label>Nama Agensi :</label><s:text name="kodAgensi.nama" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="100"/>
            </p>
            <p>
                <label>Alamat Emel :</label><s:text name="kodAgensi.emel" class="normal_text" size="30" maxlength="80" />
            </p>
            <s:button name="saveAgensi" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')" />
        </c:if>
    </fieldset>
</s:form>

