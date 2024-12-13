<%-- 
    Document   : laporanTanahWaran
    Created on : ${date}, ${time}
    Author     : faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    function save(event, f){

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');

        }

        function tambah(event, f){

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.get(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');

        }
        function hapus(index){
          
            var url = '${pageContext.request.contextPath}/strata/waranLaporanTanah?hapusKand&index='+index;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
        function papar(idMh){
          
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/strata/waran?displayItemWaran&idMh='+idMh;
        window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
   
        }
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:form name="form1" beanclass="etanah.view.strata.LaporanTanahWaranActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <p align="center">
            <div align="center">
                <table  cellspacing="5" width="100%" border="0" align="left">
                    <tr>
                        <%--<td valign="top" width="2%"><strong>1.</strong></td>--%>
                        <td width="25%" align="left" valign="top"><strong>Tajuk :</strong></td>
                        <td valign="top" width="73%">${actionBean.tajuk}</td>
                    </tr>
                    <tr>
                        <%--<td valign="top"><strong>2.</strong></td>--%>
                        <td align="left" valign="top"><strong>Pemohon :</strong></td>
                        <td>${actionBean.namaPengurusan}&nbsp;</td>
                    </tr>
                    <tr>
                        <%--<td valign="top"><strong>3.</strong></td>--%>
                        <td align="left" valign="top"><strong>Alamat Pemohon:</strong></td>
                        <td>${actionBean.alamat1} ${actionBean.alamat2} ${actionBean.alamat3} ${actionBean.alamat4}&nbsp;</td>
                    </tr>
                    <tr>
                        <%--<td valign="top"><strong>4.</strong></td>--%>
                        <td align="left" valign="top"><strong>Maklumat Pemilik Berhutang :</strong></td>
                        <td><display:table class="tablecloth" style="width:90%;" name="${actionBean.listListHakmilikPihak}" cellpadding="0" cellspacing="0" id="linemohonpihak">
                                <display:column title="Bil">${linemohonpihak_rowNum}</display:column>
                                <display:column  title="Hakmilik" property="hakmilik.idHakmilik" ></display:column>
                                <display:column title="Nama" >
                                    <c:forEach items="${linemohonpihak.listPihak}" var="line" >
                                        <p align="left"> ${line.nama}</p></c:forEach>
                                </display:column>
                                <display:column title="Notis Pertama">
                                    <p align="left">Amaun : <s:text formatPattern="#,##0.00" readonly="true" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran.amaun"></s:text> </p>
                                    <p align="left">Tarikh : <s:text readonly="true" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran.tarikhNotis"></s:text> </p>
                                    <%--
            </c:forEach>--%>
                                </display:column>
                                <display:column title="Notis Kedua">
                                    <p align="left">Amaun : <s:text formatPattern="#,##0.00" readonly="true" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran2.amaun"></s:text> </p>
                                    <p align="left">Tarikh : <s:text readonly="true" name="listListHakmilikPihak[${linemohonpihak_rowNum-1}].listNotisWaran2.tarikhNotis"></s:text> </p>

                                </display:column>
                                <display:column title="Jumlah Hutang" >
                                    <%--<p><font color="red"><b>RM 12113131.00</b></font></p>--%>
                                    <p align="center"><img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" alt="Terperinci" width="40" height="40" onclick="papar('${linemohonpihak.mohonHakmilik.id}');return false;" /></p><p align="center"><b>Papar</b></p>
                                    </display:column>
                                </display:table>&nbsp;</td>
                    </tr>
                    <%--    <tr>
                            <td>&nbsp;</td>
                            <td align="left" valign="top">&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    --%>    <tr>
                        <%--<td valign="top"><strong>5.</strong></td>--%>
                        <td align="left" valign="top"><strong>Stor Rampasan :</strong></td>
                        <td>${actionBean.simpananRampasan}</td>
                    </tr>
                    <tr>
                        <%--<td valign="top"><strong>6.</strong></td>--%>
                        <td align="left" valign="top"><strong>Pelaksana Waran :</strong></td>
                        <td>${actionBean.pelaksanaWaran}&nbsp;</td>
                    </tr>
                    <%--                <tr>
                                        <td>&nbsp;</td>
                                        <td align="left" valign="top">&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>--%>
                    <tr>
                        <%--<td valign="top"><strong>7.</strong></td>--%>
                        <td align="left" valign="top"><strong>Pelelong Awam :</strong></td>
                        <td>${actionBean.pelelongAwam}&nbsp;</td>
                    </tr>
                                    <tr>
                                        <td><strong>Tarikh Pelaksana : &nbsp;</strong></td>
                                        <td align="left" valign="top"><s:text name="tarikhPelaksana" class="datepicker"/>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                    <tr>
                    <tr>
                        
                    </tr>
                        <%--<td valign="top"><strong>8.</strong></td>--%>
                        <td align="left" valign="top"><strong>Ulasan Penolong Pegawai Tanah :</strong></td>
                        <td valign="top">
                            <c:if test="${actionBean.emptyKand eq true}">
                                <s:textarea cols="50" name="isiKand"></s:textarea> &nbsp;
                            </c:if>
                            <c:if test="${actionBean.emptyKand eq false}">
                                <c:set value="0" var="object"></c:set>
                                <c:forEach  var="line" items="${actionBean.listMohonLaporKand}" >
                                    <p>
                                        ${object+1}.
                                        <s:textarea cols="100" name="listMohonLaporKand[${object}].kand"></s:textarea>
                                        <s:hidden name="listMohonLaporKand[${object}].idKand"/>
                                        <s:hidden name="listMohonLaporKand[${object}].bil"/>
                                        <s:hidden name="listMohonLaporKand[${object}].subtajuk"/>
                                        <%--<s:hidden name="listMohonLaporKand[${object}].infoAudit.dimasukOleh"/>--%>
                                        <s:hidden name="listMohonLaporKand[${object}].laporanTanah.idLapor"/>
                                        &nbsp;<s:button name="hapusKand" value="Hapus" class="btn" onclick="hapus('${object}')"/>&nbsp;
                                    </p>
                                    <c:set  value="${object+1}" var="object" ></c:set>
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${actionBean.tambahKand eq true}">
                        <tr>
                            <%--<td>&nbsp;</td>--%>
                            <td align="left" valign="top">&nbsp;</td>
                            <td valign="top"><p>${object+1}.<s:textarea cols="100" name="isiKand"></s:textarea> &nbsp;</p></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>&nbsp;</td>
                        <%--<td align="left" valign="top">&nbsp;</td>--%>
                        <td align="left" valign="top"><p><c:if test="${actionBean.emptyKand eq false}"><s:button name="tambahKandungan" value="Tambah" class="btn" onclick="tambah(this.name, this.form)"/></c:if>&nbsp;<s:button name="simpanKand" value="Simpan" class="btn" onclick="save(this.name, this.form)"/></p></td>

                    </tr>
                </table>
                <br>
            </div><br>
        </fieldset><br>
    </div>
</s:form>
