<%-- 
    Document   : Borang E
    Created on : 23-Nov-2009, 11:55:43
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang1?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function updateDetails(h){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang1?editDetails&rowCount='+h;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function validation() {
        var count = $("#count").val();

        for(var i=1;i<=count;i++){
            var tarikhBicara = $("#tarikhBicara"+(i - 1)).val();
            var jam = $("#jam"+(i - 1)).val();
            var minit = $("#minit"+(i - 1)).val();
            var ampm = $("#ampm"+(i - 1)).val();
            var lokasiBicara = $("#lokasiBicara"+(i - 1)).val();

            if(tarikhBicara == ""){
                alert('Sila pilih " Tarikh Perbicaraan " terlebih dahulu.');
                $("#tarikhBicara"+(i - 1)).focus();
                return false;
            }
            if(jam == ""){
                alert('Sila pilih " JAM " terlebih dahulu.');
                $("#jam"+(i - 1)).focus();
                return false;
            }
            if(minit == ""){
                alert('Sila pilih " MINIT " terlebih dahulu.');
                $("#minit"+(i - 1)).focus();
                return false;
            }
            if(ampm == ""){
                alert('Sila pilih " AM/PM " terlebih dahulu.');
                $("#ampm"+(i - 1)).focus();
                return false;
            }
            if(lokasiBicara == ""){
                alert('Sila pilih " Lokasi Perbicaraan " terlebih dahulu.');
                $("#lokasiBicara"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanBorangActionBean_1">

    <s:messages/>
    <div>
        <fieldset class="aras1">
            <legend align="center">
                <b>Sila masukkan maklumat berkaitan diruang yang disediakan.</b>
            </legend>
        </fieldset>
    </div>
    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <legend align="center">
                <b>PERBICARAAN / PERUNDINGAN</b>
            </legend><br/>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList1}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_borang1" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}&nbsp;${line.hakmilik.noLot}</display:column>
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                    </display:column>
                    <display:column title="Luas Diperlukan"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;
                        <c:if test="${line.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                        <c:if test="${line.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                    </display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />

                    <%--<c:if test="${actionBean.hakmilikPerbicaraan.hakmilikPermohonan.id eq actionBean.hakmilikPermohonanList1}">--%>
                    <display:column title="Tarikh Perbicaraan">
                        <s:text name="tarikhBicara[${line_rowNum - 1}]"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhBicara${line_rowNum - 1}"/>
                        <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList1)}" id="count"/>
                    </display:column>
                    <display:column title="Waktu Perbicaraan" style="width:200px">
                        <s:select name="jam[${line_rowNum - 1}]" style="width:50px" id="jam${line_rowNum - 1}">
                            <s:option value="">Jam</s:option>
                            <s:option value="00">00</s:option>
                            <s:option value="01">01</s:option>
                            <s:option value="02">02</s:option>
                            <s:option value="03">03</s:option>
                            <s:option value="04">04</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="06">06</s:option>
                            <s:option value="07">07</s:option>
                            <s:option value="08">08</s:option>
                            <s:option value="09">09</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="11">11</s:option>
                            <s:option value="12">12</s:option>
                        </s:select>
                        <s:select name="minit[${line_rowNum - 1}]" style="width:60px" id="minit${line_rowNum - 1}">
                            <s:option value="">Minit</s:option>
                            <s:option value="00">00</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="15">15</s:option>
                            <s:option value="20">20</s:option>
                            <s:option value="25">25</s:option>
                            <s:option value="30">30</s:option>
                            <s:option value="35">35</s:option>
                            <s:option value="40">40</s:option>
                            <s:option value="45">45</s:option>
                            <s:option value="50">50</s:option>
                            <s:option value="55">55</s:option>
                        </s:select>
                        <s:select name="ampm[${line_rowNum - 1}]" style="width:60px" id="ampm${line_rowNum - 1}">
                            <s:option value="">Pilih</s:option>
                            <s:option value="AM">AM</s:option>
                            <s:option value="PM">PM</s:option>
                        </s:select>
                    </display:column>
                    <display:column title="Lokasi Perbicaraan">
                        <%--   <c:forEach items="${actionBean.idMH}" var="a">
                               <c:if test="${not empty a}">
                                   <c:if test="${a eq line.id}">
                                       ${a} :: ${line.id} === ${line_rowNum - 1}
                                       <s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="lokasiBicara" class="normal_text"/>
                                   </c:if>
                               </c:if>
                           </c:forEach>--%>
                        <s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="lokasiBicara" class="normal_text"/>
                        <%--      id a: ${actionBean.idMH[line_rowNum - 1]}<br> id : ${line.id}
                              ${fn:length(actionBean.idMH)}
                              <c:if test="${not empty actionBean.idMH[line_rowNum - 1]}">
                              <c:set value="${line.id}" var="i"/>
                               <c:set value="${actionBean.idMH[line_rowNum - 1]}" var="j"/>
                              i : ${i} ==
                              <br>j: ${j}
                              <c:if test="${actionBean.idMH[line_rowNum - 1] ne line.id}">
                                  ***
                                  <s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}" value="lokasiBicara" class="normal_text"/>
                              </c:if>--%>
                        <%--</c:if>--%>
                    </display:column>
                    <%--</c:if>--%>
                </display:table>
                <br/><br/><br/>

                <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>

                <br/>
                <br/>
                <br/>

        </fieldset>
    </div>
    <div>
        <fieldset class="aras1">
            <legend align="center">
                <b>SEJARAH</b>
            </legend><br/>
            <%--<c:if test="${edit}">--%>
            <p>
                <label for="ID_Hakmilik">ID Hakmilik :</label>${actionBean.hakmilikPerbicaraan.hakmilikPermohonan.hakmilik.idHakmilik}&nbsp;
            </p>

            <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Tangguh'}">
                <%--<c:if test="${actionBean.hakmilikPermohonan.id.hakmilikPerbicaraan.catatan eq 'Tangguh'}">--%>
                <p>
                    <label for="Nama_Pihak_Terlibat">Nama Pihak Terlibat :</label>
                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.hakmilikPerbicaraan.senaraiKehadiran}" var="senarai">
                        <c:if test="${count ne 1}">
                            <label> &nbsp;</label>
                        </c:if>
                        <c:out value="${count}"/>)&nbsp;
                        <c:choose>
                            <c:when test="${senarai.nama eq null}">
                                <font style ="text-transform: uppercase"><c:out value="${senarai.pihak.pihak.nama}"/></font><br>
                            </c:when>
                            <c:otherwise>
                                <font style ="text-transform: uppercase"><c:out value="${senarai.nama}"/></font><br>
                            </c:otherwise>
                        </c:choose>
                        <c:set value="${count + 1}" var="count"/>
                    </c:forEach>
                </p>
            </c:if>
            <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Bantahan' && actionBean.perbicaraanKehadiran.bantahElektrik eq 1}">
                <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '1'}">--%>
                <p>
                    <label for="Nama_Pihak_Terlibat">Nama Pihak Terlibat :</label>
                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.hakmilikPerbicaraan.senaraiKehadiran}" var="senarai">
                        <c:if test="${count ne 1}">
                            <label> &nbsp;</label>
                        </c:if>
                        <c:out value="${count}"/>)&nbsp;
                        <c:out value="${senarai.nama}"/><br>
                        <c:set value="${count + 1}" var="count"/>
                    </c:forEach>
                </p>
                <%--</c:if>--%>
            </c:if>
            <p>
                <label for="Tarikh_Bicara">Tarikh Bicara :</label>${actionBean.tarikhBicara1}&nbsp;
            </p>
            <p>
                <label for="Waktu_Bicara">Waktu Bicara :</label>${actionBean.time}&nbsp;
            </p>
            <p>
                <label for="Lokasi">Lokasi :</label>${actionBean.hakmilikPerbicaraan.lokasiBicara}&nbsp;
            </p>
            <p>
                <label for="Sebab">Keputusan Perbicaraan :</label>
                <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Tangguh'}">Penangguhan </c:if>
                <c:if test="${actionBean.hakmilikPerbicaraan.catatan eq 'Bantahan'}">Pertikaian </c:if>
            </p>
            <%--</c:if>--%>
        </fieldset>
    </div>
</s:form>
