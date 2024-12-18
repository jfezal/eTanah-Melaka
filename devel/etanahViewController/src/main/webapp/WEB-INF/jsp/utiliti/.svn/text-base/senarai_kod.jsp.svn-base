<%-- 
    Document   : senarai_kod
    Created on : May 8, 2012, 10:48:31 AM
    Author     : haqqiem
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>

<script type="text/javascript">
    function confirmRefresh() {
        setTimeout("location.reload(true);",200);
    }

    function refreshDocument(){
        document.location = document.location;
    }

    function testing(kod, table){
        var url = '${pageContext.request.contextPath}/utility/kod_list?popup&kod='+kod+'&table='+table;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1200,height=400");
    }

    function tambah(table){
        var url = '${pageContext.request.contextPath}/utility/kod_list?addData&table='+table;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1200,height=400");
    }
</script>

<s:form beanclass="etanah.view.utility.ListKodActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <div align="center">
                <legend>Senarai Kod-kod dalam Pangkalan Data</legend>
                <br>
                <p>
                    <s:select name="nameTable" id="" style="width:246px">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.array}"/>
                    </s:select>
                </p>
                <p>
                    <s:submit name="pilihKod" value="Pilih" class="btn"/>
                </p>

            </div>
        </fieldset>
    </div>

    <c:if test="${actionBean.rowData eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div align="center">
                    <p>
                        <s:button name="tambahData" id="tambahData" value="Tambah" class="btn" onclick="tambah('${actionBean.nameTable}');"/>
                    </p>
                </div>
                <legend>${actionBean.nameTable}</legend>
                <c:if test="${actionBean.nameTable eq 'KOD_SEKATAN'}">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiKSK}" requestURI="/utility/kod_list" id="line">
                            <display:column title="Bil" style="text-align:center">${line_rowNum}.</display:column>
                            <display:column title="KOD" property="kod"/>
                            <display:column title="KOD_CAW" property="cawangan.kod"/>
                            <display:column title="SEKATAN" property="sekatan"/>
                            <display:column title="AKTIF">
                                ${line.aktif == 'Y' ? 'Ya' : 'Tidak'}
                            </display:column>
                            <display:column title="KOD_SEKATAN" property="kodSekatan"/>
                            <display:column title="DIMASUK" property="infoAudit.dimasukOleh.idPengguna"/>
                            <display:column title="TRH_MASUK">
                                <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm a"/>
                            </display:column>
                            <display:column title="DIKKINI" property="infoAudit.dikemaskiniOleh.idPengguna"/>
                            <display:column title="TRH_KKINI">
                                <fmt:formatDate value="${line.infoAudit.tarikhKemaskini}" pattern="dd/MM/yyyy hh:mm a"/>
                            </display:column>
                            <display:column title="KEMASKINI" style="text-align:center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                                                onclick="testing('${line.kod}', '${actionBean.nameTable}');"  onmouseover="this.style.cursor='pointer';" title="Klik Untuk Kemaskini">
                            </display:column>
                        </display:table>
                    </div>
                </c:if>
                <c:if test="${actionBean.nameTable eq 'KOD_SYARAT_NYATA'}">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiKSN}" requestURI="/utility/kod_list" id="line">
                            <display:column title="Bil" style="text-align:center">${line_rowNum}.</display:column>
                            <display:column title="KOD" property="kod"/>
                            <display:column title="KOD_KATG_TANAH">
                                ${line.kategoriTanah.kod} - ${line.kategoriTanah.nama}
                            </display:column>
                            <display:column title="KOD_CAW" property="cawangan.kod"/>
                            <display:column title="SYARAT" property="syarat"/>
                            <display:column title="AKTIF">
                                ${line.aktif == 'Y' ? 'Ya' : 'Tidak'}
                            </display:column>
                            <display:column title="KOD_SYARAT" property="kodSyarat"/>
                            <display:column title="DIMASUK" property="infoAudit.dimasukOleh.idPengguna"/>
                            <display:column title="TRH_MASUK">
                                <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm a"/>
                            </display:column>
                            <display:column title="DIKKINI" property="infoAudit.dikemaskiniOleh.idPengguna"/>
                            <display:column title="TRH_KKINI">
                                <fmt:formatDate value="${line.infoAudit.tarikhKemaskini}" pattern="dd/MM/yyyy hh:mm a"/>
                            </display:column>
                            <display:column title="KEMASKINI" style="text-align:center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                                                 onclick="testing('${line.kod}', '${actionBean.nameTable}');"  onmouseover="this.style.cursor='pointer';" title="Klik Untuk Kemaskini">
                            </display:column>
                        </display:table>
                    </div>
                </c:if>
            </fieldset>
        </div>
    </c:if>
</s:form>
